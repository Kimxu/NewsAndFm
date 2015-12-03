/*
 * Copyright (C)  Tony Green, LitePal Framework Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litepal.util;

public interface Const {

	interface Model {
		/**
		 * One2One constant value.
		 */
		int ONE_TO_ONE = 1;

		/**
		 * Many2One constant value.
		 */
		int MANY_TO_ONE = 2;

		/**
		 * Many2Many constant value.
		 */
		int MANY_TO_MANY = 3;
	}

	interface LitePal {
		/**
		 * The suffix for each database file.
		 */
		String DB_NAME_SUFFIX = ".db";

		/**
		 * Constant for upper case.
		 */
		String CASES_UPPER = "upper";

		/**
		 * Constant for lower case.
		 */
		String CASES_LOWER = "lower";

		/**
		 * Constant for keep case.
		 */
		String CASES_KEEP = "keep";

		/**
		 * Constant configuration file name.
		 */
		String CONFIGURATION_FILE_NAME = "litepal.xml";
	}

	interface TableSchema {
		/**
		 * Table name in database.
		 */
		String TABLE_NAME = "table_schema";

		/**
		 * The name column in table_schema.
		 */
		String COLUMN_NAME = "name";

		/**
		 * The type column in table_schema.
		 */
		String COLUMN_TYPE = "type";

		/**
		 * Constant for normal table.
		 */
		int NORMAL_TABLE = 0;

		/**
		 * Constant for intermediate join table.
		 */
		int INTERMEDIATE_JOIN_TABLE = 1;
	}

}
