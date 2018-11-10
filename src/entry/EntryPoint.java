
package entry;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import utils.TzyUtils;

public class EntryPoint
{
	public static Logger logger = Logger.getLogger(EntryPoint.class);

	public static void main(String[] args) throws IOException
	{
		// TzyUtils.configLog4jByFile("log4j.properties");
		TzyUtils.configLog4jSimple();

		Properties prop = TzyUtils
		        .loadPropertiesFile("D:\\workspace\\CheckPrj\\test.properties");
		System.out.println(prop.toString());

	}

}
