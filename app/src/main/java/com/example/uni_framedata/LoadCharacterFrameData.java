package com.example.uni_framedata;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadCharacterFrameData {
    private String character;
    private Context myContext;
    private ArrayList<MoveData> moves;

    public static String tryDifferentName(String name){
        String output = "";
        for(int i = 0; i < name.length(); i++){
            char add = name.toLowerCase().charAt(i);
            if(add == 'a' || add == 'b' || add == 'c' || add == 'd'){
                output += 'x';
            } else {
                output += add;
            }
        }
        return output.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    }
    public static String convertInput(String input){
        String finalInput = "";
        String prev = "";
        for(int i = 0; i < input.length(); i++){
            char add = convertInputHelper(input.charAt(i));
            if (add != '+') {
                prev = "";
            }
            if(add != Character.MIN_VALUE){
                finalInput += add;
                prev += add;
            }
        }
        return finalInput;
    }
    public static char convertInputHelper(char input){
        switch(input){
            case 'A':
                return 'a';
            case 'B':
                return 'b';
            case 'C':
                return 'c';
            case 'D':
                return 'd';
            case '↑':
                return '8';
            case '↗':
                return '9';
            case '→':
                return '6';
            case '↘':
                return '3';
            case '↓':
                return '2';
            case '↙':
                return '1';
            case '←':
                return '4';
            case '↖':
                return '7';
            default:
                return Character.MIN_VALUE;
        }
    }

    public LoadCharacterFrameData(String character, Context context) {
        this.character = character;
        myContext = context;
        moves = new ArrayList<MoveData>();
        try {
            loadWords();
        } catch(IOException e){
            System.out.println("I really messed up");
        }
    }

    private void loadWords() throws IOException {
        final Resources resources = myContext.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.framedata);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Unicode"));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "\t");
                if (strings.length < 2) continue;
                String character,name, input, damage, startup, active, recovery, frameadv,cancel,guard,attribute,invincibility;
                int i = 0;
                character = strings[i++];
                if(!character.equals(this.character)) {
                    continue;
                }
                name = strings[i++];
                if (isNumeric(strings[i])) {
                    input = "";
                } else {
                    input = strings[i++];
                }
                damage = strings[i++];
                guard = strings[i++];
                startup = strings[i++];
                active = strings[i++];
                recovery = strings[i++];
                String total = strings[i++];
                frameadv = strings[i++];
                cancel = strings[i++];
                if(i < strings.length && isNumeric(strings[i].substring(0,1))) {
                    invincibility = strings[i++];
                } else {
                    invincibility = "None";
                }
                if(i < strings.length){
                    attribute = strings[i++];
                } else {
                    attribute = "None";
                }
                long id = addWord(character,name,input,damage,startup,active,recovery,frameadv,cancel,guard,attribute,invincibility);
                if (id < 0) {
                    System.out.println("Unexpected Error");
                }
            }
        } finally {
            reader.close();
        }
    }

    public long addWord(String character, String name, String input, String damage,
                        String startup, String active, String recovery
            , String frameadv, String cancel, String guard, String attribute, String invincibility) {
        MoveData newMove = new MoveData(character, name, input, damage, startup, active, recovery,
                            frameadv, cancel, guard, attribute, invincibility);
        moves.add(newMove);
        return 0;
//        return myDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
    }

    public ArrayList<MoveData> getMoves() {
        return this.moves;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
    public class MoveData{
        private String character;
        private String name;
        private String input;
        private String damage;
        private String startup;
        private String active;
        private String recovery;
        private String frameadv;
        private String cancel;
        private String guard;
        private String attribute;
        private String invincibility;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getDamage() {
            return damage;
        }

        public void setDamage(String damage) {
            this.damage = damage;
        }

        public String getStartup() {
            return startup;
        }

        public void setStartup(String startup) {
            this.startup = startup;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getRecovery() {
            return recovery;
        }

        public void setRecovery(String recovery) {
            this.recovery = recovery;
        }

        public String getFrameadv() {
            return frameadv;
        }

        public void setFrameadv(String frameadv) {
            this.frameadv = frameadv;
        }

        public String getCancel() {
            return cancel;
        }

        public void setCancel(String cancel) {
            this.cancel = cancel;
        }

        public String getGuard() {
            return guard;
        }

        public void setGuard(String guard) {
            this.guard = guard;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getInvincibility() {
            return invincibility;
        }

        public void setInvincibility(String invincibility) {
            this.invincibility = invincibility;
        }

        private MoveData(String character, String name, String input, String damage,
            String startup, String active, String recovery, String frameadv,
            String cancel, String guard, String attribute, String invincibility){
            this.character = character;
            this.name = name;
            this.input = input;
            this.damage = damage;
            this. startup = startup;
            this.active = active;
            this.recovery = recovery;
            this.frameadv = frameadv;
            this.cancel = cancel;
            this.guard = guard;
            this.attribute = attribute;
            this.invincibility = invincibility;
        }
    }
}
