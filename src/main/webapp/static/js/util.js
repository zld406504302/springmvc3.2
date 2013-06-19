/*
 *判断单一值是否为空
 */
function isNull(exp)
{
	if (!exp || exp.length ==0 || typeof(exp) =='undefined')
	{
	    return true ;
	}
	return false ;
}