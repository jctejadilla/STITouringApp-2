package com.example.stitouringapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private JSONObject scheduleData;

    // --- INNER CLASS: Message ---
    public static class Message {
        private String text;
        private boolean isUser;

        public Message(String text, boolean isUser) {
            this.text = text;
            this.isUser = isUser;
        }

        public String getText() {
            return text;
        }

        public boolean isUser() {
            return isUser;
        }
    }

    // --- INNER CLASS: MessageAdapter ---
    public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
        private static final int VIEW_TYPE_USER = 1;
        private static final int VIEW_TYPE_BOT = 2;
        private List<Message> messageList;

        public MessageAdapter(List<Message> messageList) {
            this.messageList = messageList;
        }

        @Override
        public int getItemViewType(int position) {
            return messageList.get(position).isUser() ? VIEW_TYPE_USER : VIEW_TYPE_BOT;
        }

        @NonNull
        @Override
        public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view;
            if (viewType == VIEW_TYPE_USER) {
                view = inflater.inflate(R.layout.item_message_user, parent, false);
            } else {
                view = inflater.inflate(R.layout.item_message_bot, parent, false);
            }
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            holder.textView.setText(messageList.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }

        class MessageViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            MessageViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        ImageButton sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        loadScheduleData();

        sendButton.setOnClickListener(v -> {
            String userInput = editText.getText().toString().trim();
            if (!userInput.isEmpty()) {
                addUserMessage(userInput);
                processUserInput(userInput);
                editText.setText("");
            }
        });
    }

    private void addUserMessage(String text) {
        messageList.add(new Message(text, true));
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void addBotMessage(String text) {
        messageList.add(new Message(text, false));
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void loadScheduleData() {
        try {
            InputStream is = getAssets().open("schedule.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            scheduleData = new JSONObject(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            addBotMessage("Error: Could not load schedule data.");
        }
    }

    private void processUserInput(String input) {
        String lowerCaseInput = input.toLowerCase();

        String room = null;
        // Dynamically find the room from the JSON data
        if (scheduleData != null) {
            Iterator<String> keys = scheduleData.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (lowerCaseInput.contains(key.toLowerCase())) {
                    room = key;
                    break;
                }
            }
        }

        String day = null;
        if (lowerCaseInput.contains("today")) {
            day = getDayOfWeek(0);
        } else if (lowerCaseInput.contains("tomorrow")) {
            day = getDayOfWeek(1);
        } else if (lowerCaseInput.contains("monday")) day = "Monday";
        else if (lowerCaseInput.contains("tuesday")) day = "Tuesday";
        else if (lowerCaseInput.contains("wednesday")) day = "Wednesday";
        else if (lowerCaseInput.contains("thursday")) day = "Thursday";
        else if (lowerCaseInput.contains("friday")) day = "Friday";

        if (room != null && day != null) {
            getSchedule(room, day);
        } else if (room != null) {
            getSchedule(room, getDayOfWeek(0)); // Default to today
        } else {
            addBotMessage("I can only provide room schedules. Please ask something like 'Schedule for COMLAB1 today?'");
        }
    }

    private String getDayOfWeek(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, offset);
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
    }

    private void getSchedule(String room, String day) {
        try {
            if (scheduleData.has(room)) {
                JSONObject roomSchedule = scheduleData.getJSONObject(room);
                if (roomSchedule.has(day)) {
                    String schedule = roomSchedule.getString(day);
                    addBotMessage("The schedule for " + room + " on " + day + " is: " + schedule);
                } else {
                    addBotMessage("I don't have a schedule for " + room + " on " + day + ".");
                }
            } else {
                addBotMessage("I don't know the room '" + room + "'. Please check the room number.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            addBotMessage("Error: Could not retrieve schedule.");
        }
    }
}
