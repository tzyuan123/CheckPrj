
package utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.log4j.PropertyConfigurator;

import utils.type.CmdResult;

public class TzyUtils
{

	public static void configLog4jByFile(String filePath)
	{
		PropertyConfigurator.configure(filePath);

	}

	public static void configLog4jSimple()
	{
		Properties properties = new Properties();
		// #### set log levels ###
		properties.put("log4j.rootLogger", "debug, console,file");

		// ### 杈撳嚭鍒版帶鍒跺彴 ###
		properties.put("log4j.appender.console",
		        "org.apache.log4j.ConsoleAppender");
		properties.put("log4j.appender.console.Target", "System.out");
		properties.put("log4j.appender.console.layout",
		        "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.console.layout.ConversionPattern",
		        "[%d{MM-dd HH:mm:ss,SSS} - %5p]%m%n");

		// ### 杈撳嚭鍒版枃浠� ###
		properties.put("log4j.appender.file",
		        "org.apache.log4j.RollingFileAppender");
		properties.put("log4j.appender.file.MaxBackupIndex", "3");
		properties.put("log4j.appender.file.MaxFileSize", "10MB");
		properties.put("log4j.appender.file.File", "logs/Runlog.log");
		properties.put("log4j.appender.file.Append", "true");
		properties.put("log4j.appender.file.Threshold", "DEBUG");
		properties.put("log4j.appender.file.layout",
		        "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.file.layout.ConversionPattern",
		        "[%d{yyyy-MM-dd HH:mm:ss,SSS}-%t-%5p]%m%n");

		PropertyConfigurator.configure(properties);

	}

	public static CmdResult exeCmd(String cmd)
	        throws ExecuteException, IOException
	{
		final CommandLine commandLine = CommandLine.parse(cmd);

		DefaultExecutor executor = new DefaultExecutor();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		executor.setStreamHandler(new PumpStreamHandler(baos, baos));

		int exitValue = executor.execute(commandLine);
		final String result = baos.toString().trim();

		return new CmdResult(exitValue, result);
	}

	public static CmdResult exeCmd(String cmd, int millsec)
	{
		final CommandLine commandLine = CommandLine.parse(cmd);
		final ExecuteWatchdog watchdog = new ExecuteWatchdog(millsec);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setWatchdog(watchdog);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		executor.setStreamHandler(new PumpStreamHandler(baos, baos));

		int exitValue = -1;
		try
		{
			exitValue = executor.execute(commandLine);
		}
		catch (ExecuteException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		final String result = baos.toString().trim();

		return new CmdResult(exitValue, result);
	}

	public static Properties loadPropertiesFile(String path)
	{
		Properties properties = new Properties();
		InputStream inputStream;
		try
		{
			inputStream = new BufferedInputStream(new FileInputStream(path));
			properties.load(inputStream);
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties;
	}

	public static void WriteProperties(String filePath, String pKey,
	        String pValue) throws IOException
	{
		Properties pps = new Properties();

		InputStream in = new FileInputStream(filePath);
		// 从输入流中读取属性列表（键和元素对）
		pps.load(in);
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(filePath);
		pps.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		pps.store(out, "Update " + pKey + " name");
	}

	public static void main(String[] args)
	{

	}

}
