package rebound.sql;

/**
 * Created by jcone on 8/1/15.
 */
public class ReplacingSqlParser extends AbstractSqlParser implements SqlParser {


    @Override
    protected String parseInternal(String sourceSql, SqlParameters sqlParameters) {

        StringBuilder targetSql = new StringBuilder(sourceSql.length());

        for (int nextIndex = 1, c = 0; c < sourceSql.length(); c++) {

            boolean match = false;

            // We found what could appear to be a bind variable.
            if (isPrefixToken(sourceSql.charAt(c))) {

                for (SqlParameter sp : sqlParameters) {

                    String name = normalizeParamName(sp);

                    if (sourceSql.substring(c).startsWith(name)) {
                    /*
                        We found match, now we need to determine how to bind it.
                     */
                        for (BindingResolver bindingResolver : getBindingResolvers()) {
                            String sqlFragment = bindingResolver.resolve(nextIndex, sp);

                            if (Strings.isNotBlank(sqlFragment)) {
                                nextIndex = sp.getMaxIndex() + 1;
                                targetSql.append(sqlFragment);

                                // Fast-forward the index
                                c += name.length();
                                break;
                            }
                        }
                        match = true;
                        break;
                    }
                }
            }

            if (!match) {
                targetSql.append(c);
            }

        }

        return targetSql.toString();
    }


}

