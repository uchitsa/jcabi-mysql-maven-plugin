/*
 * Copyright (c) 2012-2025 Yegor Bugayenko
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.UrlSource;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import org.junit.Test;

/**
 * Test case for {@link Foo}.
 * @since 1.0
 */
public final class WithOptionsITCase {

    /**
     * MySQL port.
     */
    private static final String PORT =
        System.getProperty("failsafe.mysql.port");

    /**
     * Can use configuration options.
     * @throws Exception If something is wrong
     */
    @Test
    public void canReceiveConfigurationOptions() throws Exception {
        final DataSource source = new UrlSource(
            String.format(
                "jdbc:mysql://localhost:%s/root?user=root&password=root",
                WithOptionsITCase.PORT
            )
        );
        new JdbcSession(source)
            .autocommit(false)
            .sql("CREATE TABLE foo (date DATE)")
            .execute()
            .sql("INSERT INTO foo VALUES ('2004-04-31')")
            .execute()
            .sql("SELECT * FROM foo")
            .execute()
            .sql("DROP TABLE foo")
            .execute();
    }

}
