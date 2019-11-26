package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

public class Main {
	protected static int LEN = 5; //Length of the input sequences
	protected static int N = 134; //Number of FSMs per block
	protected static int EXP = 1; //Number of blocks

	public static void main(String[] args) {

		//Initialization
		IOHandler IOH = new IOHandler();
		Checkups Checker = new Checkups();
		Operations Ops = new Operations();

		//File names.
		String Ifile = "binary.fst";
		String Ofile = "PearsonResults.txt";
		String Ofile2 = "SpearmanResults.txt";
//		String FEPfile = "fep.txt";
//		String Sqfile1 = "Sq1.txt";
//		String Sqfile2 = "Sq2.txt";
//		String Sqfile3 = "Sq3.txt";
//		String Sqfile4 = "Sq4.txt";
//		String Sqfile5 = "Sq5.txt";
//		String Sqfile6 = "Sq6.txt";
//		String Sqfile7 = "Sq7.txt";
//		String Sqfile8 = "Sq8.txt";
//		String Sqfile9 = "Sq9.txt";
//		String Sqfile10 = "Sq10.txt";

		//File variables
		File folder;
		FileWriter OFile;
		FileWriter OFile2;
//		FileWriter FEPFile;
//		FileWriter SqFile1;
//		FileWriter SqFile2;
//		FileWriter SqFile3;
//		FileWriter SqFile4;
//		FileWriter SqFile5;
//		FileWriter SqFile6;
//		FileWriter SqFile7;
//		FileWriter SqFile8;
//		FileWriter SqFile9;
//		FileWriter SqFile10;


		//Needed variables
		Graph G;
		Vals vals = new Vals();
		double[] FEP = new double[N];
		double[] GSq1 = new double[N];
		double[] GSq2 = new double[N];
		double[] GSq3 = new double[N];
		double[] GSq4 = new double[N];
		double[] GSq5 = new double[N];
		double[] GSq6 = new double[N];
		double[] GSq7 = new double[N];
		double[] GSq8 = new double[N];
		double[] GSq9 = new double[N];
		double[] GSq10 = new double[N];
		double Ps1 = 0;
		double Ps2 = 0;
		double Ps3 = 0;
		double Ps4 = 0;
		double Ps5 = 0;
		double Ps6 = 0;
		double Ps7 = 0;
		double Ps8 = 0;
		double Ps9 = 0;
		double Ps10 = 0;
		double Ss1 = 0;
		double Ss2 = 0;
		double Ss3 = 0;
		double Ss4 = 0;
		double Ss5 = 0;
		double Ss6 = 0;
		double Ss7 = 0;
		double Ss8 = 0;
		double Ss9 = 0;
		double Ss10 = 0;
//		double MeanPs1 = 0;
//		double MeanPs2 = 0;
//		double MeanPs3 = 0;
//		double MeanPs4 = 0;
//		double MeanPs5 = 0;
//		double MeanPs6 = 0;
//		double MeanPs7 = 0;
//		double MeanPs8 = 0;
//		double MeanPs9 = 0;
//		double MeanPs10 = 0;
//		double MeanSs1 = 0;
//		double MeanSs2 = 0;
//		double MeanSs3 = 0;
//		double MeanSs4 = 0;
//		double MeanSs5 = 0;
//		double MeanSs6 = 0;
//		double MeanSs7 = 0;
//		double MeanSs8 = 0;
//		double MeanSs9 = 0;
//		double MeanSs10 = 0;

		//Means initialization
//		MeanPs1 = 0;
//		MeanPs2 = 0;
//		MeanPs3 = 0;
//		MeanPs4 = 0;
//		MeanPs5 = 0;
//		MeanPs6 = 0;
//		MeanPs7 = 0;
//		MeanPs8 = 0;
//		MeanPs9 = 0;
//		MeanPs10 = 0;
//		MeanSs1 = 0;
//		MeanSs2 = 0;
//		MeanSs3 = 0;
//		MeanSs4 = 0;
//		MeanSs5 = 0;
//		MeanSs6 = 0;
//		MeanSs7 = 0;
//		MeanSs8 = 0;
//		MeanSs9 = 0;
//		MeanSs10 = 0;
		
		for (int K = 1; K <= 7; K++) {
			//Initialization of output file names
			//Open output files
			//Write head of output files
			try {
				Ofile = "Pearson" + String.valueOf(K*10) + ".txt";
				OFile = new FileWriter(Ofile, true);
//				OFile.write("| #Test | Pearson correlation Sq1 | Pearson correlation Sq2 | Pearson correlation Sq3 | Pearson correlation Sq4 | Pearson correlation Sq5 | Pearson correlation Sq6 | Pearson correlation Sq7 | Pearson correlation Sq8 | Pearson correlation Sq9 | Pearson correlation Sq10 |\n");
//				OFile.flush();
				Ofile2 = "Spearman" + String.valueOf(K*10) + ".txt";
				OFile2 = new FileWriter(Ofile2, true);
//				OFile2.write("| #Test | Spearman correlation Sq1 | Spearman correlation Sq2 | Spearman correlation Sq3 | Spearman correlation Sq4 | Spearman correlation Sq5 | Spearman correlation Sq6 | Spearman correlation Sq7 | Spearman correlation Sq8 | Spearman correlation Sq9 | Spearman correlation Sq10 |\n");
//				OFile2.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			//Main loop
			//INI = first test block
			//EX = number of test blocks to test
			for (int J = 0; J < EXP; J++) {

				//Initialize arrays to 0
				for (int i = 0; i < N; i++) {
					FEP[i] = 0;
					GSq1[i] = 0;
					GSq2[i] = 0;
					GSq3[i] = 0;
					GSq4[i] = 0;
					GSq5[i] = 0;
					GSq6[i] = 0;
					GSq7[i] = 0;
					GSq8[i] = 0;
					GSq9[i] = 0;
					GSq10[i] = 0;
				}

				//Initialization of output file names
//				FEPfile = "./Results/" + String.valueOf(K*10) + "/FEP/fep" + String.valueOf(J+1) + ".txt";
//				Sqfile1 = "./Results/" + String.valueOf(K*10) + "/Sq1/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile2 = "./Results/" + String.valueOf(K*10) + "/Sq2/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile3 = "./Results/" + String.valueOf(K*10) + "/Sq3/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile4 = "./Results/" + String.valueOf(K*10) + "/Sq4/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile5 = "./Results/" + String.valueOf(K*10) + "/Sq5/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile6 = "./Results/" + String.valueOf(K*10) + "/Sq6/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile7 = "./Results/" + String.valueOf(K*10) + "/Sq7/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile8 = "./Results/" + String.valueOf(K*10) + "/Sq8/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile9 = "./Results/" + String.valueOf(K*10) + "/Sq9/Sq" + String.valueOf(J+1) + ".txt";
//				Sqfile10 = "./Results/" + String.valueOf(K*10) + "/Sq10/Sq" + String.valueOf(J+1) + ".txt";

				//Initialization of input file name
//				folder = new File("./Benchmarks/BenchmarkCircuits");
				folder = new File("./SuperBenchmark (organised)/" + String.valueOf(K*10));
				
				//Block loop
				//N = number of tests per block
				int I = 0;
				for (File IFile : folder.listFiles()) {
					
					G = IOH.readGraph(IFile.toString());
//					G = IOH.buildCoffeeMachine();
//					G = IOH.buildPhone();

					if (G == null) {
						System.err.println(Ifile.toString() + ": Failled to load the automaton.");

						//Close output files
						try {
							OFile.close();
							OFile2.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}

					if (!Checker.is_valid(G.getMachine())) {
						System.err.println(Ifile.toString() + ": Non-valid graph.");
						try {
							OFile.close();
							OFile2.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}

					//Main computation
					try {
						Ops.SqueezinessAndPColl(G, LEN, vals);
						FEP[I] = vals.getPColl();
						GSq1[I] = vals.getSq1();
						GSq2[I] = vals.getSq2();
						GSq3[I] = vals.getSq3();
						GSq4[I] = vals.getSq4();
						GSq5[I] = vals.getSq5();
						GSq6[I] = vals.getSq6();
						GSq7[I] = vals.getSq7();
						GSq8[I] = vals.getSq8();
						GSq9[I] = vals.getSq9();
						GSq10[I] = vals.getSq10();

						//Test control (in order to know where we are during computation)
						System.out.println("FSM " + String.valueOf(I+1) + "\n");
						System.out.flush();

					} catch (Exception e) {
						e.printStackTrace();
					}
					I++;
				}

				//Test control (in order to know where we are during computation)
				System.out.println("test " + String.valueOf(J+1) + "\n");
				System.out.flush();

				//Open result files.
//				try {
//					FEPFile = new FileWriter(FEPfile);
//					SqFile1 = new FileWriter(Sqfile1);
//					SqFile2 = new FileWriter(Sqfile2);
//					SqFile3 = new FileWriter(Sqfile3);
//					SqFile4 = new FileWriter(Sqfile4);
//					SqFile5 = new FileWriter(Sqfile5);
//					SqFile6 = new FileWriter(Sqfile6);
//					SqFile7 = new FileWriter(Sqfile7);
//					SqFile8 = new FileWriter(Sqfile8);
//					SqFile9 = new FileWriter(Sqfile9);
//					SqFile10 = new FileWriter(Sqfile10);
//
//					//Write results to files
//					for (int i = 0; i < N; i++){
//						FEPFile.write(FEP[i] + "\n");
//						SqFile1.write(GSq1[i] + "\n");
//						SqFile2.write(GSq2[i] + "\n");
//						SqFile3.write(GSq3[i] + "\n");
//						SqFile4.write(GSq4[i] + "\n");
//						SqFile5.write(GSq5[i] + "\n");
//						SqFile6.write(GSq6[i] + "\n");
//						SqFile7.write(GSq7[i] + "\n");
//						SqFile8.write(GSq8[i] + "\n");
//						SqFile9.write(GSq9[i] + "\n");
//						SqFile10.write(GSq10[i] + "\n");
//					}
//
//					//Flush result files
//					FEPFile.flush();
//					SqFile1.flush();
//					SqFile2.flush();
//					SqFile3.flush();
//					SqFile4.flush();
//					SqFile5.flush();
//					SqFile6.flush();
//					SqFile7.flush();
//					SqFile8.flush();
//					SqFile9.flush();
//					SqFile10.flush();
//
//					//Close result files
//					FEPFile.close();
//					SqFile1.close();
//					SqFile2.close();
//					SqFile3.close();
//					SqFile4.close();
//					SqFile5.close();
//					SqFile6.close();
//					SqFile7.close();
//					SqFile8.close();
//					SqFile9.close();
//					SqFile10.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//					try {
//						OFile.close();
//						OFile2.close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//					return;
//				}

				//Compute correlations
				PearsonsCorrelation PC = new PearsonsCorrelation();
				SpearmansCorrelation SC = new SpearmansCorrelation();
				Ps1 = PC.correlation(FEP, GSq1);
				Ps2 = PC.correlation(FEP, GSq2);
				Ps3 = PC.correlation(FEP, GSq3);
				Ps4 = PC.correlation(FEP, GSq4);
				Ps5 = PC.correlation(FEP, GSq5);
				Ps6 = PC.correlation(FEP, GSq6);
				Ps7 = PC.correlation(FEP, GSq7);
				Ps8 = PC.correlation(FEP, GSq8);
				Ps9 = PC.correlation(FEP, GSq9);
				Ps10 = PC.correlation(FEP, GSq10);
				Ss1 = SC.correlation(FEP, GSq1);
				Ss2 = SC.correlation(FEP, GSq2);
				Ss3 = SC.correlation(FEP, GSq3);
				Ss4 = SC.correlation(FEP, GSq4);
				Ss5 = SC.correlation(FEP, GSq5);
				Ss6 = SC.correlation(FEP, GSq6);
				Ss7 = SC.correlation(FEP, GSq7);
				Ss8 = SC.correlation(FEP, GSq8);
				Ss9 = SC.correlation(FEP, GSq9);
				Ss10 = SC.correlation(FEP, GSq10);

				//Add to correlation means
//				MeanPs1 += Ps1;
//				MeanPs2 += Ps2;
//				MeanPs3 += Ps3;
//				MeanPs4 += Ps4;
//				MeanPs5 += Ps5;
//				MeanPs6 += Ps6;
//				MeanPs7 += Ps7;
//				MeanPs8 += Ps8;
//				MeanPs9 += Ps9;
//				MeanPs10 += Ps10;
//				MeanSs1 += Ss1;
//				MeanSs2 += Ss2;
//				MeanSs3 += Ss3;
//				MeanSs4 += Ss4;
//				MeanSs5 += Ss5;
//				MeanSs6 += Ss6;
//				MeanSs7 += Ss7;
//				MeanSs8 += Ss8;
//				MeanSs9 += Ss9;
//				MeanSs10 += Ss10;

				//Write final results
				try {
					OFile.write(String.valueOf(Ps1) + "\n");
					OFile.write(String.valueOf(Ps2) + "\n");
					OFile.write(String.valueOf(Ps3) + "\n");
					OFile.write(String.valueOf(Ps4) + "\n");
					OFile.write(String.valueOf(Ps5) + "\n");
					OFile.write(String.valueOf(Ps6) + "\n");
					OFile.write(String.valueOf(Ps7) + "\n");
					OFile.write(String.valueOf(Ps8) + "\n");
					OFile.write(String.valueOf(Ps9) + "\n");
					OFile.write(String.valueOf(Ps10) + "\n");
//						OFile.write("\\hline\n");
					OFile.flush();
					OFile2.write(String.valueOf(Ss1) + "\n");
					OFile2.write(String.valueOf(Ss2) + "\n");
					OFile2.write(String.valueOf(Ss3) + "\n");
					OFile2.write(String.valueOf(Ss4) + "\n");
					OFile2.write(String.valueOf(Ss5) + "\n");
					OFile2.write(String.valueOf(Ss6) + "\n");
					OFile2.write(String.valueOf(Ss7) + "\n");
					OFile2.write(String.valueOf(Ss8) + "\n");
					OFile2.write(String.valueOf(Ss9) + "\n");
					OFile2.write(String.valueOf(Ss10) + "\n");
//						OFile2.write("\\hline\n");
					OFile2.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			//Write final means
			try {
//				OFile.write("mean & " + String.valueOf(MeanPs1/EXP) + " & " + String.valueOf(MeanPs2/EXP) + " & " + String.valueOf(MeanPs3/EXP) + " & " + String.valueOf(MeanPs4/EXP) + " & " + String.valueOf(MeanPs5/EXP) + " & " + String.valueOf(MeanPs6/EXP) + " & " + String.valueOf(MeanPs7/EXP) + " & " + String.valueOf(MeanPs8/EXP) + " & " + String.valueOf(MeanPs9/EXP) + " & " + String.valueOf(MeanPs10/EXP) + " \\\\\n");
//				OFile.write("\\hline\n");
//				OFile.flush();
//				OFile2.write("mean & " + String.valueOf(MeanSs1/EXP) + " & " + String.valueOf(MeanSs2/EXP) + " & " + String.valueOf(MeanSs3/EXP) + " & " + String.valueOf(MeanSs4/EXP) + " & " + String.valueOf(MeanSs5/EXP) + " & " + String.valueOf(MeanSs6/EXP) + " & " + String.valueOf(MeanSs7/EXP) + " & " + String.valueOf(MeanSs8/EXP) + " & " + String.valueOf(MeanSs9/EXP) + " & " + String.valueOf(MeanSs10/EXP) + " \\\\\n");
//				OFile2.write("\\hline\n");
//				OFile2.flush();

				//Close output files
				OFile.close();
				OFile2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//End process
		return;
	}
}
