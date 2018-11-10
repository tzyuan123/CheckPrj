package utils;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class TzyUtils
{

	public static void configLog4jByFile(String filePath)
	{
		PropertyConfigurator.configure(filePath);

	}

	public static void configLog4jSimple()
	{
		Properties properties = new Properties();
//		#### set log levels ###
		properties.put("log4j.rootLogger", "debug, console,file");

//		### 输出到控制台 ###
		properties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
		properties.put("log4j.appender.console.Target", "System.out");
		properties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.console.layout.ConversionPattern", "[%d{MM-dd HH:mm:ss,SSS} - %5p]%m%n");

//		### 输出到文件 ###
		properties.put("log4j.appender.file", "org.apache.log4j.RollingFileAppender");
		properties.put("log4j.appender.file.MaxBackupIndex", "3");
		properties.put("log4j.appender.file.MaxFileSize", "10MB");
		properties.put("log4j.appender.file.File", "logs/Runlog.log");
		properties.put("log4j.appender.file.Append", "true");
		properties.put("log4j.appender.file.Threshold", "DEBUG");
		properties.put("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.file.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss,SSS}-%t-%5p]%m%n");

		PropertyConfigurator.configure(properties);

	}

	public static void main(String[] args)
	{

	}

}
