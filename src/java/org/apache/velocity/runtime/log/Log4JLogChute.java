package org.apache.velocity.runtime.log;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

import org.apache.velocity.runtime.RuntimeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.lang.reflect.Field;

/**
 * Implementation of a simple log4j system that will either latch onto
 * an existing category, or just do a simple rolling file log.
 *
 * @author <a href="mailto:geirm@apache.org>Geir Magnusson Jr.</a>
 * @author <a href="mailto:dlr@finemaltcoding.com>Daniel L. Rall</a>
 * @author <a href="mailto:nbubna@apache.org>Nathan Bubna</a>
 * @version $Id$
 * @since Velocity 1.5
 * @since 1.5
 */
public class Log4JLogChute implements LogChute
{
    public static final String RUNTIME_LOG_LOG4J_LOGGER =
            "runtime.log.logsystem.log4j.logger";
    public static final String RUNTIME_LOG_LOG4J_LOGGER_LEVEL =
            "runtime.log.logsystem.log4j.logger.level";

    private RuntimeServices rsvc = null;
    private boolean hasTrace = false;

    /**
     * <a href="http://jakarta.apache.org/log4j/">Log4J</a> logging API.
     */
    protected Logger logger = null;

    /**
     * @see org.apache.velocity.runtime.log.LogChute#init(org.apache.velocity.runtime.RuntimeServices)
     */
    public void init(RuntimeServices rs) throws Exception
    {
        rsvc = rs;

        /* first see if there is a category specified and just use that - it allows
         * the application to make us use an existing logger
         */
        String name = (String)rsvc.getProperty(RUNTIME_LOG_LOG4J_LOGGER);
        if (name != null)
        {
            logger = LoggerFactory.getLogger(name);
            log(DEBUG_ID, "Log4JLogChute using logger '" + name + '\'');
        }
        else
        {
            // create a logger with this class name to avoid conflicts
            logger = LoggerFactory.getLogger(this.getClass().getName());
        }

        /* get and set specified level for this logger */
        String lvl = rsvc.getString(RUNTIME_LOG_LOG4J_LOGGER_LEVEL);
        if (lvl != null)
        {
            Level level = Level.valueOf(lvl);
            logger.atLevel(level);
        }
        
        /* Ok, now let's see if this version of log4j supports the trace level. */
        try
        {
            Field traceLevel = Level.class.getField("TRACE");
            // we'll never get here in pre 1.2.12 log4j
            hasTrace = true;
        }
        catch (NoSuchFieldException e)
        {
            log(DEBUG_ID,
                "The version of log4j being used does not support the \"trace\" level.");
        }
    }

    /**
     *  logs messages
     *
     *  @param level severity level
     *  @param message complete error message
     */
    public void log(int level, String message)
    {
        switch (level)
        {
            case LogChute.WARN_ID:
                logger.warn(message);
                break;
            case LogChute.INFO_ID:
                logger.info(message);
                break;
            case LogChute.TRACE_ID:
                if (hasTrace)
                {
                    logger.trace(message);
                }
                else
                {
                    logger.debug(message);
                }
                break;
            case LogChute.ERROR_ID:
                logger.error(message);
                break;
            case LogChute.DEBUG_ID:
            default:
                logger.debug(message);
                break;
        }
    }

    /**
     * @see org.apache.velocity.runtime.log.LogChute#log(int, java.lang.String, java.lang.Throwable)
     */
    public void log(int level, String message, Throwable t)
    {
        switch (level)
        {
            case LogChute.WARN_ID:
                logger.warn(message, t);
                break;
            case LogChute.INFO_ID:
                logger.info(message, t);
                break;
            case LogChute.TRACE_ID:
                if (hasTrace)
                {
                    logger.trace(message, t);
                }
                else
                {
                    logger.debug(message, t);
                }
                break;
            case LogChute.ERROR_ID:
                logger.error(message, t);
                break;
            case LogChute.DEBUG_ID:
            default:
                logger.debug(message, t);
                break;
        }
    }

    /**
     * @see org.apache.velocity.runtime.log.LogChute#isLevelEnabled(int)
     */
    public boolean isLevelEnabled(int level)
    {
        switch (level)
        {
            case LogChute.DEBUG_ID:
                return logger.isDebugEnabled();
            case LogChute.INFO_ID:
                return logger.isInfoEnabled();
            case LogChute.TRACE_ID:
                if (hasTrace)
                {
                    return logger.isTraceEnabled();
                }
                else
                {
                    return logger.isDebugEnabled();
                }
            case LogChute.WARN_ID:
                return logger.isWarnEnabled();
            case LogChute.ERROR_ID:
                // can't be disabled in log4j
                return logger.isErrorEnabled();
            default:
                return true;
        }
    }

    /**
     * Also do a shutdown if the object is destroy()'d.
     * @throws Throwable
     */
    protected void finalize() throws Throwable
    {
        shutdown();
    }

    /** Close all destinations*/
    public void shutdown()
    {
    }

}
