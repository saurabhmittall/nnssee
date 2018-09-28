package sm.nse.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.nse.Constant;


public class FileOperation {
	public static void main(String[] args) {
	

	}

	@SuppressWarnings("restriction")
	public int downloadFile(int dayDiff) {
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		String dd = Utility.datetoString("ddMMyy", dayDiff);
		// https://www.nseindia.com/archives/fo/mkt/fo06042018.zip
		// String urlStr = "https://www.nseindia.com/archives/fo/bhav/fo" + dd + ".zip";
		String urlStr = "	https://www.nseindia.com/archives/equities/bhavcopy/pr/PR" + dd + ".zip";
		System.out.println("urlStr=" + urlStr);
		try {
			URL url = new URL(urlStr);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			BufferedInputStream bis = new BufferedInputStream(url.openStream()); 
			FileOutputStream fis = new FileOutputStream(Constant.localUrl+"fo" + dd + ".zip");
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = bis.read(buffer, 0, 1024)) != -1) {
				fis.write(buffer, 0, count);
			}
			fis.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String[] getLocalFileListSortOnName(String localPath) {
		File f = null;
		String[] paths = null;
		try {

			f = new File(localPath);

			paths = f.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".zip");
				}
			});

			// for each name in the path array
			// if (paths != null)
			// for (String path : paths) {
			// System.out.println(path);
			// } /**/
			Arrays.sort(paths);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paths;

	}

	public int coypFileArchive(String localBaseDir, String zipFile, String archivelocalBaseDir) {
		try {

			Path sourcePath = Paths.get(localBaseDir + "/" + zipFile);
			Path destinationPath = Paths.get(archivelocalBaseDir + "/" + zipFile);

			Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// moving file failed.
			e.printStackTrace();
		}
		return 1;
	}

	public List<String> unZipIt(String localBaseDir, String zipFile, String backupFolder) {
		System.out.println("localBaseDir=" + localBaseDir);
		System.out.println("zipFile=" + zipFile);
		List<String> nameFiles = new ArrayList<String>();
		byte[] buffer = new byte[10240];

		try {
			if (zipFile == null || !zipFile.contains("zip"))
				return null;

			String outputFolder = localBaseDir + zipFile.substring(0, zipFile.indexOf("."));

			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(localBaseDir + zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				nameFiles.add(newFile.getAbsoluteFile().getPath());
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed
				// folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);
				try {
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();
					ze = zis.getNextEntry();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}

			zis.closeEntry();
			zis.close();

			coypFileArchive(localBaseDir, zipFile, backupFolder);

		} catch (IOException ex) {
			ex.printStackTrace();

		}

		return nameFiles;
	}

}
