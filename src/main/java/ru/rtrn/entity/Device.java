package ru.rtrn.entity;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoaderException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Device {
    private static String type;
    private String community;
    private File file;
    private ArrayList<String> params;

    public abstract Mib loadMib(File file) throws MibLoaderException, IOException;
    public abstract String getType();

    @Override
    public String toString() {
        return type;
    }

    public ArrayList<String> getParams() {
        return params;
    }
}
