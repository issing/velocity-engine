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

/**
 * Implementation of a Avalon logger.
 *
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @author <a href="mailto:nbubna@apache.org">Nathan Bubna</a>
 * @version $Id$
 * @since 1.5
 */
public class AvalonLogChute implements LogChute
{
    public static final String AVALON_LOGGER = "runtime.log.logsystem.avalon.logger";
 
    public static final String AVALON_LOGGER_FORMAT = "runtime.log.logsystem.avalon.format";
    
    public static final String AVALON_LOGGER_LEVEL = "runtime.log.logsystem.avalon.level";

    private Logger logger = null;
    private RuntimeServices rsvc = null;

    /**
     * @see org.apache.velocity.runtime.log.LogChute#init(org.apache.velocity.runtime.RuntimeServices)
     */
    public void init(RuntimeServices rs) throws Exception
    {
        this.rsvc = rs;

        // if a logger is specified, we will use this instead of the default
        String name = (String)rsvc.getProperty(AVALON_LOGGER);
        if (name != null)
        {
            this.logger = LoggerFactory.getLogger(name);
        }
        else
        {
            // use the toString() of RuntimeServices to make a unique logger
            logger = LoggerFactory.getLogger(rsvc.toString());
        }
    }

    /**
     * @param file
     * @throws Exception
     * @deprecated This method should not be used. It is here only to provide
     *             backwards compatibility for the deprecated AvalonLogSystem
     *             class, in case anyone used it and this method directly.
     */
    public void init(String file) throws Exception
    {
        logger = LoggerFactory.getLogger(rsvc.toString());
        // nag the theoretical user
        log(DEBUG_ID, "You shouldn't be using the init(String file) method!");
    }

    /**
     *  logs messages
     *
     *  @param level severity level
     *  @param message complete error message
     */
    public void log(int level, String message)
    {
        /*
         * based on level, call the right logger method
         * and prefix with the appropos prefix
         */
        switch (level)
        {
            case WARN_ID:
                logger.warn(WARN_PREFIX + message );
                break;
            case INFO_ID:
                logger.info(INFO_PREFIX + message);
                break;
            case DEBUG_ID:
                logger.debug(DEBUG_PREFIX + message);
                break;
            case TRACE_ID:
                logger.debug(TRACE_PREFIX + message);
                break;
            case ERROR_ID:
                logger.error(ERROR_PREFIX + message);
                break;
            default:
                logger.info(message);
                break;
        }
    }

    /**
     *  logs messages and error
     *
     *  @param level severity level
     *  @param message complete error message
     * @param t
     */
    public void log(int level, String message, Throwable t)
    {
        switch (level)
        {
            case WARN_ID:
                logger.warn(WARN_PREFIX + message, t);
                break;
            case INFO_ID:
                logger.info(INFO_PREFIX + message, t);
                break;
            case DEBUG_ID:
                logger.debug(DEBUG_PREFIX + message, t);
                break;
            case TRACE_ID:
                logger.debug(TRACE_PREFIX + message, t);
                break;
            case ERROR_ID:
                logger.error(ERROR_PREFIX + message, t);
                break;
            default:
                logger.info(message, t);
                break;
        }
    }

    /**
     * Checks to see whether the specified level is enabled.
     * @param level
     * @return True if the specified level is enabled.
     */
    public boolean isLevelEnabled(int level)
    {
        switch (level)
        {
            // For Avalon, no Trace exists. Log at debug level.
            case TRACE_ID:
            case DEBUG_ID:
                return logger.isDebugEnabled();
            case INFO_ID:
                return logger.isInfoEnabled();
            case WARN_ID:
                return logger.isWarnEnabled();
            case ERROR_ID:
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
