package ua.university.data.XML;

import no.gorandalum.fluentresult.Result;
import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.university.domain.model.Day;
import ua.university.domain.model.Lesson;
import ua.university.domain.model.Schedule;
import ua.university.domain.repository.ScheduleRepository;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class XMLScheduleRepository implements ScheduleRepository {
    private final String fileName = "local_xml_db.xml";
    private DocumentBuilder db;
    private final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public XMLScheduleRepository() {
        dbf.setValidating(false);
    }

    @Override
    public Result<Schedule, Exception> getSchedule() {
        try {
            Schema s = sf.newSchema(new File(fileName));
            dbf.setSchema(s);
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new XMLErrorHandler());
            Document doc = db.parse(new File(fileName));

            Element root = doc.getDocumentElement();
            if (!root.getTagName().equals("schedule")) {
                return Result.error(new SAXException());
            }

            return Result.success(new Schedule(getDays(root)));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return Result.error(e);
        }
    }

    @Override
    public VoidResult<Exception> saveSchedule(@NotNull Schedule schedule) {
        try {
            Schema s = sf.newSchema(new File(fileName));
            dbf.setSchema(s);
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new XMLErrorHandler());
            Document doc = db.parse(new File(fileName));

            Element root = doc.createElement("schedule");
            doc.appendChild(root);
            writeDays(doc, root, schedule.getSchedules());

            Source domSource  = new DOMSource(doc);
            javax.xml.transform.Result fileResult = new StreamResult(new File(fileName));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            transformer.transform(domSource, fileResult);

            return VoidResult.success();
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
            return VoidResult.error(e);
        }
    }

    private @NotNull List<Day> getDays(@NotNull Element root) {
        List<Day> days = new ArrayList<>();
        NodeList dayList = root.getElementsByTagName("day");
        for (int i = 0; i < dayList.getLength(); i++) {
            Element day = (Element) dayList.item(i);
            days.add(getDay(day));
        }
        return days;
    }

    @Contract("_ -> new")
    private @NotNull Day getDay(@NotNull Element day) {
        String dayId = day.getAttribute("id");
        String dayOfWeek = day.getAttribute("day_of_week");
        return new Day(
                Integer.parseInt(dayId),
                Integer.parseInt(dayOfWeek),
                getLessons(day)
        );
    }

    private @NotNull List<Lesson> getLessons(@NotNull Element day) {
        List<Lesson> lessons = new ArrayList<>();
        NodeList lessonList = day.getElementsByTagName("lesson");
        for (int j = 0; j < lessonList.getLength(); j++) {
            Element lesson = (Element) lessonList.item(j);
            lessons.add(getLesson(lesson));
        }
        return lessons;
    }

    @Contract("_ -> new")
    private @NotNull Lesson getLesson(@NotNull Element lesson) {
        String lessonId = lesson.getAttribute("id");
        String lessonSubjectName = lesson.getAttribute("subject_name");
        String lessonTeacherName = lesson.getAttribute("teacher_name");
        String lessonStartTime = lesson.getAttribute("start_time");
        String lessonEndTime = lesson.getAttribute("end_time");

        return new Lesson(
                Integer.parseInt(lessonId),
                lessonSubjectName,
                lessonTeacherName,
                LocalTime.parse(lessonStartTime),
                LocalTime.parse(lessonEndTime)
        );
    }

    private void writeDays(@NotNull Document doc, @NotNull Element root, @NotNull List<Day> days) {
        for (Day day: days) {
            writeDay(doc, root, day);
        }
    }

    private void writeDay(@NotNull Document doc, @NotNull Element root, @NotNull Day day) {
        Element dayElement = doc.createElement("day");
        dayElement.setAttribute("id", String.valueOf(day.getId()));
        dayElement.setAttribute("day_of_week", String.valueOf(day.getDayOfWeek()));
        root.appendChild(dayElement);
        writeLessons(doc, dayElement, day.getLessons());
    }

    private void writeLessons(@NotNull Document doc, @NotNull Element day, @NotNull List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            writeLesson(doc, day, lesson);
        }
    }

    private void writeLesson(@NotNull Document doc, @NotNull Element day, @NotNull Lesson lesson) {
        Element lessonElement = doc.createElement("lesson");
        lessonElement.setAttribute("id", String.valueOf(lesson.getId()));
        lessonElement.setAttribute("subject_name", String.valueOf(lesson.getSubjectName()));
        lessonElement.setAttribute("teacher_name", String.valueOf(lesson.getTeacherName()));
        lessonElement.setAttribute("start_time", String.valueOf(lesson.getStartTime()));
        lessonElement.setAttribute("end_time", String.valueOf(lesson.getEndTime()));
        day.appendChild(lessonElement);
    }
}
