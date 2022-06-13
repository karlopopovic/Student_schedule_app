package com.example.student_schedule_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import  com.example.student_schedule_app.model.Activity;
import com.example.student_schedule_app.Database.UserFirestoreManager;
import com.example.student_schedule_app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
//import android.app.Activity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.student_schedule_app.Database.ActivityFirestoreManager;
import com.example.student_schedule_app.Database.CollageFirestoreManager;
import com.example.student_schedule_app.Database.CourseFirestoreManager;
import com.example.student_schedule_app.Database.DegreeFirestoreManager;
import com.example.student_schedule_app.Database.MajorFirestoreManager;
import com.example.student_schedule_app.Database.RoomFirestoreManager;
import com.example.student_schedule_app.Database.TypeFirestoreManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.student_schedule_app.adapter.DayAdapter;
import com.example.student_schedule_app.listeners.OnLongDayViewItemClickListener;
import com.example.student_schedule_app.model.Day;
import com.example.student_schedule_app.networking.DataService;
import com.example.student_schedule_app.networking.Repository;
import com.example.student_schedule_app.ui.login.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DayAdapter adapter;
    private FloatingActionButton fab,bulk_btn;
    private OnLongDayViewItemClickListener longDayViewItemClickListener;
    private MaterialDatePicker picker;
    private MaterialDatePicker.Builder builder;
    private TextView textView;
    private Toolbar toolbar;
    private com.google.android.material.appbar.AppBarLayout toolbarLayout;
    private ImageView imageView;
    private ImageView imageView2;
    private TextView date, username, role;
    private DatePickerDialog.OnDateSetListener listener;
    private Context context = null;
    private DatabaseReference databaseReference;
    private Object QuerySnapshot;
    private Date date1;
    private ArrayList<Activity> activityArrayList;
    FirebaseFirestore db;
    MaterialDatePicker materialDatePicker;
    MaterialDatePicker.Builder materialDateBuilder;
    private CollectionReference activitiesCollectionReference;
    private ActivityFirestoreManager activitesFirestoreManager;
    private CollageFirestoreManager collagesFirestoreManager;
    private CourseFirestoreManager coursesFirestoreManager;
    private DegreeFirestoreManager degreesFirestoreManager;
    private MajorFirestoreManager majorsFirestoreManager;
    private RoomFirestoreManager roomsFirestoreManager;
    private TypeFirestoreManager typesFirestoreManager;
    private UserFirestoreManager usersFirestoreManager;
    private ArrayList<String> courses = new ArrayList<String>();
    private User current_user;
    private View popupView;
    private Dialog dialog;
    private CardView cardView;
    private Timestamp currentTimestamp;
    private ArrayList<Activity> activities;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activitesFirestoreManager = ActivityFirestoreManager.newInstance();
        collagesFirestoreManager = CollageFirestoreManager.newInstance();
        coursesFirestoreManager = CourseFirestoreManager.newInstance();
        degreesFirestoreManager = DegreeFirestoreManager.newInstance();
        majorsFirestoreManager = MajorFirestoreManager.newInstance();
        roomsFirestoreManager = RoomFirestoreManager.newInstance();
        typesFirestoreManager = TypeFirestoreManager.newInstance();
        usersFirestoreManager = UserFirestoreManager.newInstance();
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDatePicker = materialDateBuilder.build();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        DataService service = Repository.getRetrofitInstance().create(DataService.class);
        recyclerView = findViewById(R.id.recycler);
        dialog = new Dialog(this);
        activityArrayList = new ArrayList<Activity>();;


        android.app.Activity act= (android.app.Activity) context;

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");


            }

        });
        setView();
        getUser();
        setListeners();

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    void setView() {


        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        builder = MaterialDatePicker.Builder.datePicker();
        picker = builder.build();
        toolbarLayout = findViewById(R.id.appBarLayout);
        cardView=findViewById(R.id.card);
        toolbar.setCollapseContentDescription("Hello");
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recycler);
        date = findViewById(R.id.textView);
        username = findViewById(R.id.textView2);
        role = findViewById(R.id.textView3);
        imageView2 = findViewById(R.id.imageView2);

        ArrayList<Activity> activities = new ArrayList<>();
        ArrayList<Day> days = new ArrayList<>();
        adapter = new DayAdapter(new ArrayList<Day>(), new OnLongDayViewItemClickListener() {
            @Override
            public void OnLongClick(int position) {
                if (role.getText().toString().equals("admin")) {
                    CardView card = findViewById(R.id.card);
                    PopupMenu menu = new PopupMenu(getApplicationContext(), card);
                    menu.inflate(R.menu.basic_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_delete:
                                    delete_activity(position);
                                    return true;
                                default:
                                    return false;
                            }
                        }

                    });
                    menu.show();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void delete_activity(int position){

        String activity_id = activities.get(position).getActivityId();
        activitesFirestoreManager.deleteActivity(activity_id);
        if (role.getText().toString().equals("admin"))
            activitesFirestoreManager.getActivities(currentTimestamp, new GetActivitiesListener());
        else{
            activitesFirestoreManager.getActivities(currentTimestamp, new GetCertainActivitiesListener());
        }

    }

    void setListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPositiveButtonClick(Object selection) {
                Toast.makeText(MainActivity.this, picker.getHeaderText(), Toast.LENGTH_SHORT).show();
                date.setText(picker.getHeaderText());
                SimpleDateFormat formatter = new SimpleDateFormat("dd. MMM yyyy.");
                try {
                    Date date = formatter.parse(picker.getHeaderText());
                    Toast.makeText(MainActivity.this, "formated: "+date.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("MAIN_ACT",date.toString());
                    Log.d("MAIN_ACT",role.getText().toString());
                    currentTimestamp = new Timestamp(date);
                    if (role.getText().toString().equals("admin")) {
                        activitesFirestoreManager.getActivities(new Timestamp(date), new GetActivitiesListener());
                      
                    }

                    else{
                        activitesFirestoreManager.getActivities(new Timestamp(date), new GetCertainActivitiesListener());

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    formatter = new SimpleDateFormat("MMM dd, yyyy");
                    Date date = null;
                    try {
                        date = formatter.parse(picker.getHeaderText());
                        Toast.makeText(MainActivity.this, "formated: "+date.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("MAIN_ACT",date.toString());
                        Log.d("MAIN_ACT",role.getText().toString());
                        currentTimestamp = new Timestamp(date);
                        if (role.getText().toString().equals("admin"))
                            activitesFirestoreManager.getActivities(new Timestamp(date), new GetActivitiesListener());

                        else{
                            activitesFirestoreManager.getActivities(new Timestamp(date), new GetCertainActivitiesListener());


                        }
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }

                }
            }
        });





        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });


    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private class GetActivitiesListener implements OnCompleteListener<QuerySnapshot>{



        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful()) {
                ArrayList<Day> days = new ArrayList<>();
                Log.d("MAIN_ACT", "task successfull: " + task.getResult().toString());
                Log.d("MAIN_ACT", "len of ans: " + task.getResult().size());
                activities = new ArrayList<>();
                for (DocumentSnapshot doc : task.getResult()) {

                        days.add(activityToDay(doc.toObject(Activity.class)));
                        activities.add(doc.toObject(Activity.class));
                }

                        adapter.setDays(days);
                        adapter.notifyDataSetChanged();

            }
            else{
                Log.d("MAIN_ACT","task unsuccessfull");
            }
        }
    }

    public void getUser(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("MAIN_ACT", user.getUid());
            Long time = System.currentTimeMillis();
            Log.d("MAIN_ACT", time.toString());
            usersFirestoreManager.getUser(user.getUid(), new GetUsersListener());
        }
    }

    private class GetUsersListener implements OnCompleteListener<DocumentSnapshot> {

        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()){

                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot != null){
                    User current_user = documentSnapshot.toObject(User.class);
                    Log.d("MAIN_ACT",current_user.getName());
                    updateUI(current_user);
                    courses= current_user.getCourses();
                }
            }
        }
    }



    @SuppressLint("ResourceType")
    public void updateUI(User user){


        username.setText(user.getName());
        role.setText(user.getRole());


        if (user.getRole().equals("admin")){


           fab.setVisibility(View.VISIBLE);
        }
       else{
           courses = user.getCourses();


            fab.setVisibility(View.INVISIBLE);
        }
    }



    private class GetCertainActivitiesListener implements OnCompleteListener<QuerySnapshot>{


        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful()){
                ArrayList<Day> days = new ArrayList<>();

                Log.d("MAIN_ACT","task successfull: "+task.getResult().toString());
                Log.d("MAIN_ACT","courses: "+courses);
                activities = new ArrayList<>();
                for (DocumentSnapshot doc: task.getResult()){
                    if (courses.contains(doc.toObject(Activity.class).getCourseName())) {
                        days.add(activityToDay(doc.toObject(Activity.class)));
                        activities.add(doc.toObject(Activity.class));
                    }

                }
                adapter.setDays(days);
                adapter.notifyDataSetChanged();
            }
            else{
                Log.d("MAIN_ACT","task unsuccessfull");
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Day activityToDay(Activity activity){
        return new Day(activity.getCourseName(),activity.getRoom(),getDayDate(activity.getBegin_time()),getDayDate(activity.getEnd_time()));
        
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDayDate(Timestamp timestamp){
        LocalDateTime date = dateToLocalDateTime(timestamp.toDate());
        return date.getHour() +":"+ date.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate dateToLocal(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date localToDate(LocalDate localDate){
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

}