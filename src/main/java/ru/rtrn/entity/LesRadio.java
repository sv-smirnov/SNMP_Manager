package ru.rtrn.entity;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.percederberg.mibble.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LesRadio extends Device{
    private static final String type = "SW-212HDAE";
    private static final String community = "private";
    private static final File file = new File("src/main/resources/mibs/1.csv");
    private final Mib mib;
    private ArrayList<String> params;
    private int port;

    public List<String[]> readCsv() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> list = reader.readAll();
        return list;
    }

    public LesRadio() throws MibLoaderException, IOException {
        mib = loadMib(file);
        loadParams();
    }

    public LesRadio(int port) throws MibLoaderException, IOException {
        mib = loadMib(file);
        this.port = port;
    }

    @Override
    public Mib loadMib(File file) throws MibLoaderException, IOException {
        MibLoader loader = new MibLoader();
        Mib mib;
        file = file.getAbsoluteFile();
        try {
            loader.addDir(file.getParentFile());
            mib = loader.load(file);
        } catch (MibLoaderException e) {
            e.getLog().printTo(System.err);
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return mib;
    }
    private ArrayList<String> loadParams(){
        params = new ArrayList<>(mib.getAllSymbols());
        return params;
    }

    @Override
    public String getType(){
        return type;
    }

    public ArrayList<String> getParams() {
        return params;
    }
}
