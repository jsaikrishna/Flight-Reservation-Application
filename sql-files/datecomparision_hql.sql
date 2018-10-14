HibernateUtil.getSession().createQuery(
   "from Foo WHERE activationDate >= :rolloffDate)
   .setTimestamp(getRolloffDate()).list();