package persistence;

import org.json.JSONObject;

//Note: classes within persistence package including are from
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    JSONObject toJson();
}
