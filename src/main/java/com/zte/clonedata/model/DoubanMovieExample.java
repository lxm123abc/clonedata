package com.zte.clonedata.model;

import java.util.ArrayList;
import java.util.List;

public class DoubanMovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DoubanMovieExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMovieidIsNull() {
            addCriterion("movieid is null");
            return (Criteria) this;
        }

        public Criteria andMovieidIsNotNull() {
            addCriterion("movieid is not null");
            return (Criteria) this;
        }

        public Criteria andMovieidEqualTo(String value) {
            addCriterion("movieid =", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidNotEqualTo(String value) {
            addCriterion("movieid <>", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidGreaterThan(String value) {
            addCriterion("movieid >", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidGreaterThanOrEqualTo(String value) {
            addCriterion("movieid >=", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidLessThan(String value) {
            addCriterion("movieid <", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidLessThanOrEqualTo(String value) {
            addCriterion("movieid <=", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidLike(String value) {
            addCriterion("movieid like", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidNotLike(String value) {
            addCriterion("movieid not like", value, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidIn(List<String> values) {
            addCriterion("movieid in", values, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidNotIn(List<String> values) {
            addCriterion("movieid not in", values, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidBetween(String value1, String value2) {
            addCriterion("movieid between", value1, value2, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovieidNotBetween(String value1, String value2) {
            addCriterion("movieid not between", value1, value2, "movieid");
            return (Criteria) this;
        }

        public Criteria andMovienameIsNull() {
            addCriterion("moviename is null");
            return (Criteria) this;
        }

        public Criteria andMovienameIsNotNull() {
            addCriterion("moviename is not null");
            return (Criteria) this;
        }

        public Criteria andMovienameEqualTo(String value) {
            addCriterion("moviename =", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameNotEqualTo(String value) {
            addCriterion("moviename <>", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameGreaterThan(String value) {
            addCriterion("moviename >", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameGreaterThanOrEqualTo(String value) {
            addCriterion("moviename >=", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameLessThan(String value) {
            addCriterion("moviename <", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameLessThanOrEqualTo(String value) {
            addCriterion("moviename <=", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameLike(String value) {
            addCriterion("moviename like", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameNotLike(String value) {
            addCriterion("moviename not like", value, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameIn(List<String> values) {
            addCriterion("moviename in", values, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameNotIn(List<String> values) {
            addCriterion("moviename not in", values, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameBetween(String value1, String value2) {
            addCriterion("moviename between", value1, value2, "moviename");
            return (Criteria) this;
        }

        public Criteria andMovienameNotBetween(String value1, String value2) {
            addCriterion("moviename not between", value1, value2, "moviename");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNull() {
            addCriterion("director is null");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNotNull() {
            addCriterion("director is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorEqualTo(String value) {
            addCriterion("director =", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotEqualTo(String value) {
            addCriterion("director <>", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThan(String value) {
            addCriterion("director >", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThanOrEqualTo(String value) {
            addCriterion("director >=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThan(String value) {
            addCriterion("director <", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThanOrEqualTo(String value) {
            addCriterion("director <=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLike(String value) {
            addCriterion("director like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotLike(String value) {
            addCriterion("director not like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorIn(List<String> values) {
            addCriterion("director in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotIn(List<String> values) {
            addCriterion("director not in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorBetween(String value1, String value2) {
            addCriterion("director between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotBetween(String value1, String value2) {
            addCriterion("director not between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andScenaristIsNull() {
            addCriterion("scenarist is null");
            return (Criteria) this;
        }

        public Criteria andScenaristIsNotNull() {
            addCriterion("scenarist is not null");
            return (Criteria) this;
        }

        public Criteria andScenaristEqualTo(String value) {
            addCriterion("scenarist =", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristNotEqualTo(String value) {
            addCriterion("scenarist <>", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristGreaterThan(String value) {
            addCriterion("scenarist >", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristGreaterThanOrEqualTo(String value) {
            addCriterion("scenarist >=", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristLessThan(String value) {
            addCriterion("scenarist <", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristLessThanOrEqualTo(String value) {
            addCriterion("scenarist <=", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristLike(String value) {
            addCriterion("scenarist like", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristNotLike(String value) {
            addCriterion("scenarist not like", value, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristIn(List<String> values) {
            addCriterion("scenarist in", values, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristNotIn(List<String> values) {
            addCriterion("scenarist not in", values, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristBetween(String value1, String value2) {
            addCriterion("scenarist between", value1, value2, "scenarist");
            return (Criteria) this;
        }

        public Criteria andScenaristNotBetween(String value1, String value2) {
            addCriterion("scenarist not between", value1, value2, "scenarist");
            return (Criteria) this;
        }

        public Criteria andActorsIsNull() {
            addCriterion("actors is null");
            return (Criteria) this;
        }

        public Criteria andActorsIsNotNull() {
            addCriterion("actors is not null");
            return (Criteria) this;
        }

        public Criteria andActorsEqualTo(String value) {
            addCriterion("actors =", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsNotEqualTo(String value) {
            addCriterion("actors <>", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsGreaterThan(String value) {
            addCriterion("actors >", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsGreaterThanOrEqualTo(String value) {
            addCriterion("actors >=", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsLessThan(String value) {
            addCriterion("actors <", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsLessThanOrEqualTo(String value) {
            addCriterion("actors <=", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsLike(String value) {
            addCriterion("actors like", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsNotLike(String value) {
            addCriterion("actors not like", value, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsIn(List<String> values) {
            addCriterion("actors in", values, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsNotIn(List<String> values) {
            addCriterion("actors not in", values, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsBetween(String value1, String value2) {
            addCriterion("actors between", value1, value2, "actors");
            return (Criteria) this;
        }

        public Criteria andActorsNotBetween(String value1, String value2) {
            addCriterion("actors not between", value1, value2, "actors");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andReleasedataIsNull() {
            addCriterion("releasedata is null");
            return (Criteria) this;
        }

        public Criteria andReleasedataIsNotNull() {
            addCriterion("releasedata is not null");
            return (Criteria) this;
        }

        public Criteria andReleasedataEqualTo(String value) {
            addCriterion("releasedata =", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataNotEqualTo(String value) {
            addCriterion("releasedata <>", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataGreaterThan(String value) {
            addCriterion("releasedata >", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataGreaterThanOrEqualTo(String value) {
            addCriterion("releasedata >=", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataLessThan(String value) {
            addCriterion("releasedata <", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataLessThanOrEqualTo(String value) {
            addCriterion("releasedata <=", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataLike(String value) {
            addCriterion("releasedata like", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataNotLike(String value) {
            addCriterion("releasedata not like", value, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataIn(List<String> values) {
            addCriterion("releasedata in", values, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataNotIn(List<String> values) {
            addCriterion("releasedata not in", values, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataBetween(String value1, String value2) {
            addCriterion("releasedata between", value1, value2, "releasedata");
            return (Criteria) this;
        }

        public Criteria andReleasedataNotBetween(String value1, String value2) {
            addCriterion("releasedata not between", value1, value2, "releasedata");
            return (Criteria) this;
        }

        public Criteria andRuntimeIsNull() {
            addCriterion("runtime is null");
            return (Criteria) this;
        }

        public Criteria andRuntimeIsNotNull() {
            addCriterion("runtime is not null");
            return (Criteria) this;
        }

        public Criteria andRuntimeEqualTo(String value) {
            addCriterion("runtime =", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotEqualTo(String value) {
            addCriterion("runtime <>", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeGreaterThan(String value) {
            addCriterion("runtime >", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeGreaterThanOrEqualTo(String value) {
            addCriterion("runtime >=", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeLessThan(String value) {
            addCriterion("runtime <", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeLessThanOrEqualTo(String value) {
            addCriterion("runtime <=", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeLike(String value) {
            addCriterion("runtime like", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotLike(String value) {
            addCriterion("runtime not like", value, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeIn(List<String> values) {
            addCriterion("runtime in", values, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotIn(List<String> values) {
            addCriterion("runtime not in", values, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeBetween(String value1, String value2) {
            addCriterion("runtime between", value1, value2, "runtime");
            return (Criteria) this;
        }

        public Criteria andRuntimeNotBetween(String value1, String value2) {
            addCriterion("runtime not between", value1, value2, "runtime");
            return (Criteria) this;
        }

        public Criteria andRatingnumIsNull() {
            addCriterion("ratingnum is null");
            return (Criteria) this;
        }

        public Criteria andRatingnumIsNotNull() {
            addCriterion("ratingnum is not null");
            return (Criteria) this;
        }

        public Criteria andRatingnumEqualTo(String value) {
            addCriterion("ratingnum =", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumNotEqualTo(String value) {
            addCriterion("ratingnum <>", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumGreaterThan(String value) {
            addCriterion("ratingnum >", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumGreaterThanOrEqualTo(String value) {
            addCriterion("ratingnum >=", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumLessThan(String value) {
            addCriterion("ratingnum <", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumLessThanOrEqualTo(String value) {
            addCriterion("ratingnum <=", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumLike(String value) {
            addCriterion("ratingnum like", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumNotLike(String value) {
            addCriterion("ratingnum not like", value, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumIn(List<String> values) {
            addCriterion("ratingnum in", values, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumNotIn(List<String> values) {
            addCriterion("ratingnum not in", values, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumBetween(String value1, String value2) {
            addCriterion("ratingnum between", value1, value2, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andRatingnumNotBetween(String value1, String value2) {
            addCriterion("ratingnum not between", value1, value2, "ratingnum");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andMoviedescIsNull() {
            addCriterion("moviedesc is null");
            return (Criteria) this;
        }

        public Criteria andMoviedescIsNotNull() {
            addCriterion("moviedesc is not null");
            return (Criteria) this;
        }

        public Criteria andMoviedescEqualTo(String value) {
            addCriterion("moviedesc =", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescNotEqualTo(String value) {
            addCriterion("moviedesc <>", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescGreaterThan(String value) {
            addCriterion("moviedesc >", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescGreaterThanOrEqualTo(String value) {
            addCriterion("moviedesc >=", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescLessThan(String value) {
            addCriterion("moviedesc <", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescLessThanOrEqualTo(String value) {
            addCriterion("moviedesc <=", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescLike(String value) {
            addCriterion("moviedesc like", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescNotLike(String value) {
            addCriterion("moviedesc not like", value, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescIn(List<String> values) {
            addCriterion("moviedesc in", values, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescNotIn(List<String> values) {
            addCriterion("moviedesc not in", values, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescBetween(String value1, String value2) {
            addCriterion("moviedesc between", value1, value2, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andMoviedescNotBetween(String value1, String value2) {
            addCriterion("moviedesc not between", value1, value2, "moviedesc");
            return (Criteria) this;
        }

        public Criteria andPDateIsNull() {
            addCriterion("p_date is null");
            return (Criteria) this;
        }

        public Criteria andPDateIsNotNull() {
            addCriterion("p_date is not null");
            return (Criteria) this;
        }

        public Criteria andPDateEqualTo(String value) {
            addCriterion("p_date =", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateNotEqualTo(String value) {
            addCriterion("p_date <>", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateGreaterThan(String value) {
            addCriterion("p_date >", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateGreaterThanOrEqualTo(String value) {
            addCriterion("p_date >=", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateLessThan(String value) {
            addCriterion("p_date <", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateLessThanOrEqualTo(String value) {
            addCriterion("p_date <=", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateLike(String value) {
            addCriterion("p_date like", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateNotLike(String value) {
            addCriterion("p_date not like", value, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateIn(List<String> values) {
            addCriterion("p_date in", values, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateNotIn(List<String> values) {
            addCriterion("p_date not in", values, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateBetween(String value1, String value2) {
            addCriterion("p_date between", value1, value2, "pDate");
            return (Criteria) this;
        }

        public Criteria andPDateNotBetween(String value1, String value2) {
            addCriterion("p_date not between", value1, value2, "pDate");
            return (Criteria) this;
        }

        public Criteria andAkaIsNull() {
            addCriterion("aka is null");
            return (Criteria) this;
        }

        public Criteria andAkaIsNotNull() {
            addCriterion("aka is not null");
            return (Criteria) this;
        }

        public Criteria andAkaEqualTo(String value) {
            addCriterion("aka =", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaNotEqualTo(String value) {
            addCriterion("aka <>", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaGreaterThan(String value) {
            addCriterion("aka >", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaGreaterThanOrEqualTo(String value) {
            addCriterion("aka >=", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaLessThan(String value) {
            addCriterion("aka <", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaLessThanOrEqualTo(String value) {
            addCriterion("aka <=", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaLike(String value) {
            addCriterion("aka like", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaNotLike(String value) {
            addCriterion("aka not like", value, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaIn(List<String> values) {
            addCriterion("aka in", values, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaNotIn(List<String> values) {
            addCriterion("aka not in", values, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaBetween(String value1, String value2) {
            addCriterion("aka between", value1, value2, "aka");
            return (Criteria) this;
        }

        public Criteria andAkaNotBetween(String value1, String value2) {
            addCriterion("aka not between", value1, value2, "aka");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}