package gitlet;
import java.io.Serializable;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;


public class Commit implements Serializable {
    /**
     *
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    private String parents;
    private String ownRefHash;
    /** Stores the mapping of files and SHA-1 IDS of
     * file's contents added to this commit.*/
    private HashMap<String, String> blobs; //<files, SHA1>

    public Commit(String message, HashMap<String, String> blobMap, String parents) {
        this.message = message;
        Date currentTime = new Date();
        this.timestamp = dateToTimeStamp(currentTime);
        this.blobs = blobMap;
        this.parents = parents;
        this.ownRefHash = createID(); //calculate sha1 of blob
    }

    public Commit() {
        this.message = "initial commit";
        Date currentTime = new Date(0);
        this.timestamp = dateToTimeStamp(currentTime);
        this.ownRefHash = Utils.sha1(timestamp);
        this.blobs = new HashMap<>();
    }

    public String createID() {
        return Utils.sha1(timestamp, message, parents, blobs.toString());
    }

    public String getOwnRefHash() {
        return ownRefHash;
    }

    public String getParentHash() {
        return parents;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public HashMap<String, String> getBlobs() {
        return blobs;
    }

    public String getIDFromFile(String fileName) {
        if (!blobs.containsKey(fileName)) {
            return null;
        }
        return blobs.get(fileName);
    }

   public String getFileFromFile(String sha1) {
        for (String fileName:blobs.keySet()) {
            if (blobs.get(fileName).equals(sha1)) {
                return fileName;
            }
        }
        return null;
    }

    /**
     * displays information about all commits ever made
     * i.e
     * ===
     * commit a0da1ea5a15ab613bf9961fd86f010cf74c7ee48
     * Date: Thu Nov 9 20:00:05 2017 -0800
     * A commit message.
     */


    public static String dateToTimeStamp(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);
        return dateFormat.format(date);
    }

    public void setParents(String parent) {
        this.parents = parent;
    }

    public boolean tracked(String fileName) {
        return blobs.containsKey(fileName);
    }


}

