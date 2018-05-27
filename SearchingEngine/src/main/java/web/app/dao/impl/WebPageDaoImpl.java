package web.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import web.app.dao.WebPageDao;
import web.app.dao.generic.AbstractBaseDao;
import web.app.entities.WebPage;

@Repository
public class WebPageDaoImpl extends AbstractBaseDao<WebPage, Long> implements WebPageDao {

	public WebPageDaoImpl() {
		this.type = WebPage.class;
	}

	@Override
	public List<WebPage> getWebPagesByUrl(String url) {
		// Search with LIKE
		Criteria crit = (Criteria) getCurrentSession().createCriteria(WebPage.class)
				.add(Restrictions.like("webUrl", url));

		List<WebPage> result = crit.list();

		return result;
	}

	@Override
	public List<WebPage> findByUrl(String url) {
		Criteria crit = (Criteria) getCurrentSession().createCriteria(WebPage.class)
				.add(Restrictions.eq("webUrl", url));

		List<WebPage> result = crit.list();
		return result;
	}

	@Override
	public List<WebPage> findByUrlAndKeyword(String url, String keyword) {
		Criteria crit = (Criteria) getCurrentSession().createCriteria(WebPage.class);

		crit.add(Restrictions.eq("webUrl", url))
		.add(Restrictions.or(
				Restrictions.ilike("webTitle", "%" + keyword.trim() + "%"),
				Restrictions.ilike("webParagraph", "%" + keyword.trim() + "%")
				 ));
		
		// .add( Restrictions.like("webTitle", keyword.trim()));
//		 .add(Restrictions.or(
//		 Restrictions.like("webTitle", keyword),
//		 Restrictions.like("webParagraph", keyword)
//		 ));
		// crit.add(Restrictions.like("webTitle", keyword));
		// crit.add(Restrictions.like("webParagraph", keyword));

		List<WebPage> result = crit.list();
		return result;
	}

	@Override
	public void sessionFlush() {
		getCurrentSession().flush();
	}
}
