// Generated code from Butter Knife. Do not modify!
package in.headrun.buzzinga.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TwitterLogin$$ViewBinder<T extends in.headrun.buzzinga.activities.TwitterLogin> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624166, "field 'loginButton'");
    target.loginButton = finder.castView(view, 2131624166, "field 'loginButton'");
    view = finder.findRequiredView(source, 2131624169, "field 'progressbar'");
    target.progressbar = finder.castView(view, 2131624169, "field 'progressbar'");
    view = finder.findRequiredView(source, 2131624165, "field 'twitter_btn'");
    target.twitter_btn = view;
    view = finder.findRequiredView(source, 2131624168, "field 'twitter_auth_lay'");
    target.twitter_auth_lay = view;
    view = finder.findRequiredView(source, 2131624098, "field 'webview'");
    target.webview = finder.castView(view, 2131624098, "field 'webview'");
  }

  @Override public void unbind(T target) {
    target.loginButton = null;
    target.progressbar = null;
    target.twitter_btn = null;
    target.twitter_auth_lay = null;
    target.webview = null;
  }
}
