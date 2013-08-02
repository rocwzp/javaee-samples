package com.sivalabs.tweeter.exceptions;

/**
 * @author Siva
 *
 */
public class TweeterException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public TweeterException()
	{
	}

	public TweeterException(String message)
	{
		super(message);
	}

	public TweeterException(Throwable cause)
	{
		super(cause);
	}

	public TweeterException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
