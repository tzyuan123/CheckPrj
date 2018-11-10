package entry;

import org.apache.log4j.Logger;

import utils.TzyUtils;

public class EntryPoint
{
	public static Logger logger = Logger.getLogger(EntryPoint.class);

	public static void main(String[] args)
	{
//		TzyUtils.configLog4jByFile("log4j.properties");
		TzyUtils.configLog4jSimple();

		for (int i = 0; i < 100; i++)
		{
			logger.info("test");
			logger.debug("test");
			logger.error("test");
			logger.info("===test");

		}

	}

}
