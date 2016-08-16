import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
// 작업 시작을 위한 메인메소드.
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Scanner;


public class starter {
	public static void main (String [] args){
				
		Scanner s = new Scanner(System.in);
		dataManager data = new dataManager(); 
		lockerManager locker = new lockerManager();
		randomer randomer = new randomer();

		lockerFrame framer = new lockerFrame();
		
		framer.start();
		
	}
}
