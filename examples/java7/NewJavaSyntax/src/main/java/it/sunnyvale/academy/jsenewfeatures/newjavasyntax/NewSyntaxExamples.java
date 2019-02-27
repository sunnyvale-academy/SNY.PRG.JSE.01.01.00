/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sunnyvale.academy.jsenewfeatures.newjavasyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author denismaggiorotto
 */
public class NewSyntaxExamples {

    long creditCardNumber = 1234_5678_9012_3456L;
    long socialSecurityNumber = 999_99_9999L;
    long hexBytes = 0xFF_EC_DE_5E;
    long hexWords = 0xCAFE_BABE;
    long maxLong = 0x7fff_ffff_ffff_ffffL;
    byte nybbles = 0b0010_0101;
    long bytes = 0b11010010_01101001_10010100_10010010;

    // An 8-bit 'byte' value:
    byte aByte = 0b0010_0001;

    // A 16-bit 'short' value:
    short aShort = (short) 0b1010_0001_0100_0101;

    // Some 32-bit 'int' values:
    int anInt1 = 0b1010_0001_0100_0101_1010_0001_0100_0101;
    int anInt2 = 0b101;
    int anInt3 = 0B101; // The B can be upper or lower case.

    public static final short[] HAPPY_FACE = {
        (short) 0b0000011111100000,
        (short) 0b0000100000010000,
        (short) 0b0001000000001000,
        (short) 0b0010000000000100,
        (short) 0b0100000000000010,
        (short) 0b1000011001100001,
        (short) 0b1000011001100001,
        (short) 0b1000000000000001,
        (short) 0b1000000000000001,
        (short) 0b1001000000001001,
        (short) 0b1000100000010001,
        (short) 0b0100011111100010,
        (short) 0b0010000000000100,
        (short) 0b0001000000001000,
        (short) 0b0000100000010000,
        (short) 0b0000011111100000
    };

    public String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
        String typeOfDay;
        switch (dayOfWeekArg) {
            case "Monday":
                typeOfDay = "Start of work week";
                break;
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
                typeOfDay = "Midweek";
                break;
            case "Friday":
                typeOfDay = "End of work week";
                break;
            case "Saturday":
            case "Sunday":
                typeOfDay = "Weekend";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
        }
        return typeOfDay;
    }

    // Pre Java SE 7 style
    Map<String, List<String>> myMapOld = new HashMap<String, List<String>>();

    // Java SE 7 style
    Map<String, List<String>> myMapNew = new HashMap<>();

    // Wrong style
    Map<String, List<String>> myMapWrong = new HashMap(); // unchecked conversion warning

    static String readFirstLineFromFile(String path) {
        String toReturn =null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            toReturn = br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println(t.getMessage());
            }
        }
        return  toReturn;
    }

    public static void main(String[] args) {
        //CAN'T BE RUN, FOR SYNTAX DEMO ONLY
    }

}
