package pt.brunojesus.truck.foundation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAOP {

	private static final int LOG_LEVEL_DEBUG = 2;
	private static final int LOG_LEVEL_ERROR = 5;

	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Bean annotated with auto logger.
	 */
	@Pointcut("within(@AutoLogger *)")
	public void beanAnnotatedWithAutoLogger() {
	}

	/**
	 * Method inside A class marked with auto logger.
	 */
	@Pointcut("beanAnnotatedWithAutoLogger()")
	public void methodInsideAClassMarkedWithAutoLogger() {
	}

	/**
	 * Class log before.
	 *
	 * @param joinPoint        the join point
	 * @param loggerAnnotation the logger annotation
	 */
	@Before("beanAnnotatedWithAutoLogger()")
	public void classLogBefore(JoinPoint joinPoint) {
		doLog(LOG_LEVEL_DEBUG, joinPoint, "STARTED ", null);
	}

	/**
	 * Class log after.
	 *
	 * @param joinPoint        the join point
	 * @param loggerAnnotation the logger annotation
	 */
	@After("beanAnnotatedWithAutoLogger()")
	public void classLogAfter(JoinPoint joinPoint) {
		doLog(LOG_LEVEL_DEBUG, joinPoint, "FINISHED ", null);
	}

	/**
	 * Method log before.
	 *
	 * @param joinPoint        the join point
	 * @param loggerAnnotation the logger annotation
	 */
	@Before("@annotation(loggerAnnotation)")
	public void methodLogBefore(JoinPoint joinPoint, AutoLogger loggerAnnotation) {
		doLog(LOG_LEVEL_DEBUG, joinPoint, "STARTED ", null);
	}

	/**
	 * Method log after.
	 *
	 * @param joinPoint        the join point
	 * @param loggerAnnotation the logger annotation
	 */
	@After("@annotation(loggerAnnotation)")
	public void methodLogAfter(JoinPoint joinPoint, AutoLogger loggerAnnotation) {
		doLog(LOG_LEVEL_DEBUG, joinPoint, "FINISHED ", null);
	}

	/**
	 * Log error.
	 *
	 * @param joinPoint the join point
	 * @param ex        the ex
	 */
	@AfterThrowing(pointcut = "execution(* pt.brunojesus.truck..*.* (..))", throwing = "ex")
	public void logError(JoinPoint joinPoint, Exception ex) {
		doLog(LOG_LEVEL_ERROR, joinPoint, "ERROR ", ex.getClass().getSimpleName());
	}

	/**
	 * Do log.
	 *
	 * @param logLevel  the log level
	 * @param joinPoint the join point
	 * @param preText   the pre text
	 * @param details   the details
	 */
	private void doLog(int logLevel, JoinPoint joinPoint, String preText, String details) {
		StringBuilder logMsg = new StringBuilder();
		logMsg.append(preText);
		logMsg.append(joinPoint.getTarget().getClass().getSimpleName());
		logMsg.append(".");
		logMsg.append(joinPoint.getSignature().getName());
		if (details != null) {
			logMsg.append(" - ");
			logMsg.append(details);
		}

		switch (logLevel) {
		case LOG_LEVEL_ERROR:
			log.error(logMsg.toString());
			break;
		case LOG_LEVEL_DEBUG:
		default:
			log.debug(logMsg.toString());
			break;
		}
	}
}
