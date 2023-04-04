/*******************************************************************************
 * Copyright (c) 2010 Denis Solonenko.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Denis Solonenko - initial API and implementation
 ******************************************************************************/
package ru.orangesoftware.financisto.report;

import android.content.Context;
import ru.orangesoftware.financisto.blotter.BlotterFilter;
import ru.orangesoftware.financisto.filter.WhereFilter;
import ru.orangesoftware.financisto.filter.Criteria;
import ru.orangesoftware.financisto.db.DatabaseAdapter;
import ru.orangesoftware.financisto.model.Currency;

import static ru.orangesoftware.financisto.db.DatabaseHelper.V_REPORT_ACC_TYPES;
import static ru.orangesoftware.financisto.db.DatabaseHelper.V_REPORT_PAYEES;

public class AccTypeReport extends Report {

	public AccTypeReport(Context context, Currency currency) {
		super(ReportType.BY_ACC_TYPE, context, currency);
	}

	@Override
	public ReportData getReport(DatabaseAdapter db, WhereFilter filter) {
        cleanupFilter(filter);

		if(this.reportType.toString()=="BY_ACC_TYPE")
		{
			filter.remove("from_account_id");
		}
		return queryReport(db, V_REPORT_ACC_TYPES, filter);
	}

	@Override
	public Criteria getCriteriaForId(DatabaseAdapter db, long id) {
		return Criteria.eq(BlotterFilter.PAYEE_ID, String.valueOf(id));
	}		
	
}
