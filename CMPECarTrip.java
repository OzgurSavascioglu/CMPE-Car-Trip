import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CMPECarTrip {
	//static variables
	public static CMPECarTrip myCarTrip;
	public int askCount=0;
	
	/**
	 * constructor
	 */
	public CMPECarTrip() {
		myCarTrip=this;
	}//end of the constructor
	
	public static void newCarTrip(Scanner songFile, Scanner testFile, PrintWriter outputFile) throws IOException {//the method receive input file 1 and call the constructor
		new CMPECarTrip();//call the constructor
		
		//calls the methods to read the songFile and test file
		Song.createSongList(songFile);
		playlistReader(testFile);
		//fastEventReader(testFile, outputFile);
		//fastEventReader2(testFile, outputFile);
		//fastEventReader3(testFile, outputFile);
		fastEventReader5(testFile, outputFile);
		//fastEventReaderNew(testFile, outputFile);
	}//end of the start the game method

	private static void playlistReader(Scanner testFile) {
		int index=0;
		Song[] temp=new Song[Song.listSize];
		
		Song.setPlaylistCL(testFile.nextInt());
		Playlist.setHeartacheCL(testFile.nextInt());
		Playlist.setRoadtripCL(testFile.nextInt());
		Playlist.setBlissfulCL(testFile.nextInt());
		
		int noOfLists= testFile.nextInt();
		
		PlaylistHashTable.setTableSize(PlaylistHashTable.nextPrime(noOfLists*2+1));
		Playlist.allLists=new PlaylistHashTable();
		
		for(int i=0;i<noOfLists;i++) {
			//String[] lineReader=testFile.nextLine().split(" ");
			int ID=testFile.nextInt();
			int size=testFile.nextInt();
			Playlist.allLists.insert(new Playlist(ID, size));
			
			//String[] listReader=testFile.nextLine().split(" ");
			if(size==0) {
				String skip=testFile.nextLine();
			}
			
			for(int j=0;j<size;j++) {
				Song newSong=Song.allSongs[testFile.nextInt()-1];
				//Song newSong=Song.songList.searchTable(testFile.nextInt());
				newSong.setPlayListID(ID);
				newSong.setInList(true);
				temp[index]=newSong;
				index++;
			}		
		}	
		
		Song.hSort= new	Song[index];
		Song.rSort= new	Song[index];
		Song.bSort= new	Song[index];
		
		System.arraycopy(temp, 0, Song.hSort, 0, index);
		System.arraycopy(temp, 0, Song.rSort, 0, index);
		System.arraycopy(temp, 0, Song.bSort, 0, index);

		Song.mergeSort("hSort",(Song.hSort));
		Song.mergeSort("rSort",(Song.rSort));
		Song.mergeSort("bSort",(Song.bSort));
		
		//Playlist.createInitialBlends();
		Playlist.createInitialBlends5();
		/*//JUST TO TEST
		System.out.println("HSORT: ");
		Song.printHSort();
		
		System.out.println("RSORT: ");
		Song.printRSort();
		
		System.out.println("BSORT: ");
		Song.printBSort();*/
		
		//System.out.println("allSongs: ");
		//Song.printAllSongs();
		
	}
	
	private static void fastEventReader3(Scanner testFile, PrintWriter outputFile) throws IOException {
		int eventCount=testFile.nextInt();
		int eventNo=0;

		while(eventNo<eventCount) {
			String eventType=testFile.next();
			
			if(eventType.equals("REM")) {
				Playlist.fastRemove3(testFile.nextInt(),testFile.nextInt(), outputFile);
			}
			
			else if(eventType.equals("ADD")) {
				Playlist.fastAdd3(testFile.nextInt(),testFile.nextInt(), outputFile);
			}
			
			else if(eventType.equals("ASK")) {
				if(myCarTrip.askCount<5) {
					Playlist.fastAsk(outputFile);
					myCarTrip.askCount++;
				}
			}
		eventNo++;
		}
	}
	
	private static void fastEventReader5(Scanner testFile, PrintWriter outputFile) throws IOException {
		int eventCount=testFile.nextInt();
		int eventNo=0;

		while(eventNo<eventCount) {
			String eventType=testFile.next();
			
			if(eventType.equals("REM")) {
				Playlist.fastRemove5(testFile.nextInt(),testFile.nextInt(), outputFile);
			}
			
			else if(eventType.equals("ADD")) {
				Playlist.fastAdd5(testFile.nextInt(),testFile.nextInt(), outputFile);
			}
			
			else if(eventType.equals("ASK")) {
				if(myCarTrip.askCount<5) {
					Playlist.fastAsk(outputFile);
					myCarTrip.askCount++;
				}
			}
		eventNo++;
		}
	}

}


