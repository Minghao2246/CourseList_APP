package persistence;

import model.Course;
import model.CourseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads course list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses course list from JSON object and returns it
    private CourseList parseWorkRoom(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
        CourseList courseList = new CourseList();
        addCourses(courseList, jsonObject);
        return courseList;
    }

    // MODIFIES: courseList
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addCourses(CourseList courseList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addIt(courseList, nextThingy);
        }
    }

    // MODIFIES: courseList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addIt(CourseList courseList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int grade = jsonObject.getInt("grade");
        String comment = jsonObject.getString("comment");
        Course course = new Course(name, grade, comment);
        courseList.addCourse(course);
    }
}
