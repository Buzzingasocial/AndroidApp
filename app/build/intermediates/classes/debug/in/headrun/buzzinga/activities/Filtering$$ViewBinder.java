// Generated code from Butter Knife. Do not modify!
package in.headrun.buzzinga.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Filtering$$ViewBinder<T extends in.headrun.buzzinga.activities.Filtering> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624154, "field 'filter_titles'");
    target.filter_titles = finder.castView(view, 2131624154, "field 'filter_titles'");
    view = finder.findRequiredView(source, 2131624156, "field 'filter_items'");
    target.filter_items = finder.castView(view, 2131624156, "field 'filter_items'");
    view = finder.findRequiredView(source, 2131624157, "field 'clearfilter'");
    target.clearfilter = finder.castView(view, 2131624157, "field 'clearfilter'");
    view = finder.findRequiredView(source, 2131624158, "field 'applyfilter'");
    target.applyfilter = finder.castView(view, 2131624158, "field 'applyfilter'");
    view = finder.findRequiredView(source, 2131624155, "field 'autosearch'");
    target.autosearch = finder.castView(view, 2131624155, "field 'autosearch'");
  }

  @Override public void unbind(T target) {
    target.filter_titles = null;
    target.filter_items = null;
    target.clearfilter = null;
    target.applyfilter = null;
    target.autosearch = null;
  }
}
