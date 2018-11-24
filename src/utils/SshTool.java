
package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshTool
{
	Logger logger = Logger.getLogger(SshTool.class);

	private String ip = "";

	private int port = 22;

	private String user = "";

	private String password = "";

	private Session session = null;

	private Channel channel = null;

	public SshTool(String ip, int port, String user, String password)
	{
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;

		JSch jsch = new JSch();
		try
		{
			session = jsch.getSession(user, ip, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
		}
		catch (JSchException e)
		{
			logger.error("", e);
		}

	}

	public String getIp()
	{
		return ip;
	}

	public int getPort()
	{
		return port;
	}

	public String getUser()
	{
		return user;
	}

	public String getPassword()
	{
		return password;
	}

	public void connect()
	{
		try
		{
			session.connect(60 * 1000);
		}
		catch (JSchException e)
		{
			logger.error("", e);
		}
	}

	public String sendCmd(String cmd)
	{
		StringBuffer sbf = new StringBuffer();
		try
		{
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);

			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true)
			{
				while (in.available() > 0)
				{
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					sbf.append(new String(tmp, 0, i));
				}
				if (channel.isClosed())
				{
					if (in.available() > 0)
						continue;
					channel.getExitStatus();
					break;
				}
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception ee)
				{
				}
			}

		}
		catch (JSchException e)
		{
			logger.error("", e);
		}
		catch (IOException e)
		{
			logger.error("", e);
		}

		return sbf.toString();
	}

	public int getExitStatus()
	{
		if (channel != null)
		{
			return channel.getExitStatus();
		} else
		{
			return -1;
		}
	}

	public void disconnect()
	{
		channel.disconnect();
		session.disconnect();
	}

}
