package planner;

import tx.Transaction;
import query.Plan;
import parse.QueryData;

/**
 * The interface implemented by planners for the SQL select statement.
 * 
 * @author Edward Sciore
 *
 */
public interface QueryPlanner {

	/**
	 * Creates a plan for the parsed query.
	 * 
	 * @param data
	 *            the parsed representation of the query
	 * @param tx
	 *            the calling transaction
	 * @return a plan for that query
	 */
	public Plan createPlan(QueryData data, Transaction tx);
}
