package com.example.student_schedule_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.time.ZoneId;

import com.example.student_schedule_app.Database.ActivityFirestoreManager;
import com.example.student_schedule_app.Database.CollageFirestoreManager;
import com.example.student_schedule_app.Database.CourseFirestoreManager;
import com.example.student_schedule_app.Database.DegreeFirestoreManager;
import com.example.student_schedule_app.Database.MajorFirestoreManager;
import com.example.student_schedule_app.Database.RoomFirestoreManager;
import com.example.student_schedule_app.Database.TypeFirestoreManager;
import com.example.student_schedule_app.model.Activity;
import com.example.student_schedule_app.model.Admin;
import com.example.student_schedule_app.model.Course;
import com.example.student_schedule_app.model.Degree;
import com.example.student_schedule_app.model.Major;
import com.example.student_schedule_app.model.Room;
import com.example.student_schedule_app.model.Collage;
import com.example.student_schedule_app.model.Type;
import com.example.student_schedule_app.networking.DataService;
import com.example.student_schedule_app.networking.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private AutoCompleteTextView collage_sp, major_sp, course_sp, degree_sp, room_sp, type_sp, hour_sp, min_sp;

    private FloatingActionButton fab;
    private DataService service;
    private List<Admin> a_list;
    private String collage = "", major= "", course= "", degree= "", room= "", type= "";
    //private Room room;
    private Button begin_time;
    private Button end_time;
    private Button date;
    private int hour,minute;
    private CollageFirestoreManager collagesFirestoreManager;
    private DegreeFirestoreManager degreesFirestoreManager;
    private MajorFirestoreManager majorsFirestoreManager;
    private RoomFirestoreManager roomsFirestoreManager;
    private ActivityFirestoreManager activitesFirestoreManager;
    private CourseFirestoreManager coursesFirestoreManager;
    private TypeFirestoreManager typesFirestoreManager;
    private TextInputLayout outlinedTextField;
    private TextInputLayout outlinedTextField2;
    private TextInputLayout outlinedTextField3;
    private TextInputLayout outlinedTextField4;
    private TextInputLayout outlinedTextField5;
    private TextInputLayout outlinedTextField6;
    private TextInputEditText description_text;
    private MaterialTimePicker time_picker;
    private MaterialDatePicker.Builder builder;
    private MaterialDatePicker date_picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        collagesFirestoreManager = CollageFirestoreManager.newInstance();
        degreesFirestoreManager = DegreeFirestoreManager.newInstance();
        majorsFirestoreManager = MajorFirestoreManager.newInstance();
        roomsFirestoreManager = RoomFirestoreManager.newInstance();
        activitesFirestoreManager = ActivityFirestoreManager.newInstance();
        typesFirestoreManager = TypeFirestoreManager.newInstance();
        coursesFirestoreManager = CourseFirestoreManager.newInstance();
        setView();
        setListeners();
        service = Repository.getRetrofitInstance().create(DataService.class);




    }



    @SuppressLint("WrongViewCast")
    private void setView(){

        fab = findViewById(R.id.add_fab);

        begin_time = findViewById(R.id.button);

        end_time = findViewById(R.id.button2);

        date = findViewById(R.id.button3);


        outlinedTextField = findViewById(R.id.outlinedTextField);

        outlinedTextField2 = findViewById(R.id.outlinedTextField2);

        outlinedTextField3 = findViewById(R.id.outlinedTextField3);

        outlinedTextField4 = findViewById(R.id.outlinedTextField4);

        outlinedTextField5 = findViewById(R.id.outlinedTextField5);

        outlinedTextField6 = findViewById(R.id.outlinedTextField6);

        description_text = findViewById(R.id.description_text);

        collage_sp = findViewById(R.id.collage_spinner);


        major_sp = findViewById(R.id.major_spinner);


        degree_sp = findViewById(R.id.degree_spinner);


        course_sp = findViewById(R.id.course_spinner);


        room_sp = findViewById(R.id.room_spinner);

        type_sp = findViewById(R.id.type_spinner);

        builder = MaterialDatePicker.Builder.datePicker();
        date_picker = builder.build();


        getData();



    }

    private void getData(){
        collagesFirestoreManager.getAllCollages(new GetAllCollagesListener());
        degreesFirestoreManager.getAllDegrees(new GetAllDegreesListener());
        majorsFirestoreManager.getAllMajors(new GetAllMajorsListener());
        roomsFirestoreManager.getAllRooms(new GetAllRoomsListener());
        typesFirestoreManager.getAllTypes(new GetAllTypesListener());
        coursesFirestoreManager.getAllCourses(new GetAllCoursesListener());
    }

    private void setListeners(){

        collage_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                collage = parent.getAdapter().getItem(position).toString();
            }


        });

        major_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                major = parent.getAdapter().getItem(position).toString();
            }

        });

        course_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                course = parent.getAdapter().getItem(position).toString();
            }


        });

        degree_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                degree = parent.getAdapter().getItem(position).toString();
            }


        });

        room_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                room = parent.getAdapter().getItem(position).toString();
            }

        });

        type_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getAdapter().getItem(position).toString();
            }


        });

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (!description_text.getText().toString().equals("") && !collage.equals("") && !course.equals("") && !degree.equals("")
                        && !major.equals("") && !room.equals("") && !type.equals("")) {

                    try {


                        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMM yyyy.");
                        Date activity_date = formatter.parse(date.getText().toString());

                        Timestamp begin_timestamp = getTimestamp(begin_time.getText().toString(), activity_date);
                        Timestamp end_timestamp = getTimestamp(end_time.getText().toString(), activity_date);

                        Activity act = new Activity(collage,
                                major,
                                course,
                                room,
                                degree,
                                type,
                                description_text.getText().toString(),
                                begin_timestamp,
                                end_timestamp);
                        checkConstraints(act, activity_date);

                    } catch (ParseException e) {
                        e.printStackTrace();

                        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
                        Date activity_date = null;
                        try {
                            activity_date = formatter.parse(date.getText().toString());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }

                        Timestamp begin_timestamp = getTimestamp(begin_time.getText().toString(), activity_date);
                        Timestamp end_timestamp = getTimestamp(end_time.getText().toString(), activity_date);

                        Activity act = new Activity(collage,
                                major,
                                course,
                                room,
                                degree,
                                type,
                                description_text.getText().toString(),
                                begin_timestamp,
                                end_timestamp);
                        checkConstraints(act, activity_date);
                    }


                }
                else{
                    Toast.makeText(AddActivity.this, "Please fill everything.", Toast.LENGTH_SHORT).show();
                    Log.d("KARLO_SA","d:"+description_text.getText().toString()+" collage:"+collage+" major:"+major
                            +" degree:"+degree+" course:"+course+" room:"+room+" type:"+type);
                }
            }

        });

        begin_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_picker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD).build();
                time_picker.show(getSupportFragmentManager(), "tag");
                time_picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(AddActivity.this, String.valueOf(time_picker.getInputMode()), Toast.LENGTH_SHORT).show();

                        begin_time.setText(timeToString(time_picker.getHour(), time_picker.getMinute()));
                    }
                });
            }
        });


        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_picker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD).build();
                time_picker.show(getSupportFragmentManager(), "tag");
                time_picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(AddActivity.this, String.valueOf(time_picker.getInputMode()), Toast.LENGTH_SHORT).show();
                        end_time.setText(timeToString(time_picker.getHour(), time_picker.getMinute()));
                    }
                });
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_picker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });



        date_picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPositiveButtonClick(Object selection) {
                Toast.makeText(AddActivity.this, date_picker.getHeaderText(), Toast.LENGTH_SHORT).show();
                date.setText(date_picker.getHeaderText());
            }
        });


    }

    public String timeToString(int hours, int minutes){
        String hours_string = hours >= 10 ? String.valueOf(hours) : "0"+String.valueOf(hours);
        String min_string = minutes >= 10 ? String.valueOf(minutes) : "0"+String.valueOf(minutes);

        return hours_string+":"+min_string;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Timestamp getTimestamp(String time_string, Date activity_date){

        String[] time_split = time_string.split(":");

        int hours = Integer.parseInt(time_split[0]);
        int minutes = Integer.parseInt(time_split[1]);

        LocalDateTime localDateTime = dateToLocalDateTime(activity_date);
        localDateTime = localDateTime.withHour(hours).withMinute(minutes);

        return new Timestamp(localToDateTime(localDateTime));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date localToDateTime(LocalDateTime localDate){
        return java.util.Date
                .from(localDate.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkConstraints (Activity activity, Date certain_date){

        activitesFirestoreManager.getActivities(new Timestamp(certain_date), new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    //check for activities that satisfy one of 4 constraints
                    ArrayList<Activity> act_list = new ArrayList<>();
                    for (DocumentSnapshot doc : task.getResult()){
                        Activity act = doc.toObject(Activity.class);
                        if ( (act.getBegin_time().compareTo(activity.getBegin_time())<0 && act.getEnd_time().compareTo(activity.getBegin_time()) <= 0) || (act.getBegin_time().compareTo(activity.getEnd_time())>=0 && act.getEnd_time().compareTo(activity.getEnd_time())>0) )
                            continue;
                        else
                            act_list.add(act);
                    }
                    Log.d("ADD_ACT","list size: "+String.valueOf(act_list.size()));

                    //check is the room avaliable
                    //is it is that save the activity, otherwise cancel
                    int flag = 0;
                    for (Activity act : act_list){
                        if (act.getRoom().equals(activity.getRoom())){
                            Toast.makeText(AddActivity.this, "Activity can't be saved", Toast.LENGTH_SHORT).show();
                            Log.d("ACT_ADD","Activity can't be saved");
                            flag = 1;
                            break;
                              }
                    }
                    if (flag==0){
                        activitesFirestoreManager.createActivity(activity);
                        Toast.makeText(AddActivity.this, "Activity saved", Toast.LENGTH_SHORT).show();
                        Log.d("ACT_ADD","Activity saved");
                    }
                }
            }
        });

    }

    private class GetAllCollagesListener implements OnCompleteListener<QuerySnapshot> {

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    collage_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Collage.class)));
                }
            }
        }
    }

    private class GetAllDegreesListener implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    degree_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Degree.class)));
                }
            }
        }
    }

    private class GetAllMajorsListener implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    major_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Major.class)));
                }
            }
        }
    }

    private class GetAllRoomsListener implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    room_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Room.class)));
                }
            }
        }
    }

    private class GetAllTypesListener implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    type_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Type.class)));
                }
            }
        }
    }

    private class GetAllCoursesListener implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null){
                    course_sp.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, querySnapshot.toObjects(Course.class)));
                }
            }
        }
    }

}