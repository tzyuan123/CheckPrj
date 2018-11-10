
package utils.type;

public class CmdResult
{
	private int exitValue = -1;

	private String result = null;

	public int getExitValue()
	{
		return exitValue;
	}

	public void setExitValue(int exitValue)
	{
		this.exitValue = exitValue;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public CmdResult(int exitValue, String result)
	{
		this.exitValue = exitValue;
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "CmdResult [exitValue=" + exitValue + ", result=" + result + "]";
	}

}
