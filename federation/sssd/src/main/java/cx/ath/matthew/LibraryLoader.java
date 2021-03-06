/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cx.ath.matthew;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:bruno@abstractj.org">Bruno Oliveira</a>.
 */
public class LibraryLoader {

    private static final Logger LOGGER = Logger.getLogger(LibraryLoader.class.getSimpleName());

    private static final String[] PATHS = {"/usr/lib/", "/usr/lib64/", "/usr/local/lib/", "/opt/local/lib/"};
    private static final String LIBRARY_NAME = "libunix_dbus_java";
    private static final String VERSION = "0.0.8";
    private static boolean loadSucceeded;

    public static void load() {
        for (String path : PATHS) {
            try {
                System.load(String.format("%s/%s.so.%s", path, LIBRARY_NAME, VERSION));
                loadSucceeded = true;
                break;
            } catch (UnsatisfiedLinkError e) {
                loadSucceeded = false;
            }

        }

        if (!loadSucceeded) LOGGER.log(Level.WARNING, "libunix_dbus_java not found\n" +
                "Please, make sure you have the package libunix-dbus-java installed.");
    }
}
