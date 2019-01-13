package application.design;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Const {
    public static String el = "x^2 / a ^2 + y^2 / b^2 = 1";
    public static String gip = "x^2 / a ^2 - y^2 / b^2 = 1";
    public static String par = "y^2=2px";
    public static ObservableList<String> list = FXCollections.observableArrayList(el, gip, par);
}
