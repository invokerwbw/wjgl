!function(a,b){'object'==typeof exports?module.exports=b(a,require('jquery')):'function'==typeof define&&define.amd?define(['jquery'],function(c){return b(a,c)}):b(a,a.jQuery)}(this,function(a,b,c){jQuery.fn.extend({slimScroll:function(c){var d={width:'auto',height:'250px',size:'7px',color:'#000',position:'right',distance:'1px',start:'top',opacity:.4,alwaysVisible:!1,disableFadeOut:!1,railVisible:!1,railColor:'#333',railOpacity:.2,railDraggable:!0,railClass:'slimScrollRail',barClass:'slimScrollBar',wrapperClass:'slimScrollDiv',allowPageScroll:!1,wheelStep:20,touchScrollStep:200,borderRadius:'7px',railBorderRadius:'7px'},e=b.extend(d,c);return this.each(function(){function d(c){if(k){var c=c||a.event,d=0;c.wheelDelta&&(d=-c.wheelDelta/120),c.detail&&(d=c.detail/3);var g=c.target||c.srcTarget||c.srcElement;b(g).closest('.'+e.wrapperClass).is(w.parent())&&f(d,!0),c.preventDefault&&!v&&c.preventDefault(),v||(c.returnValue=!1)}}function f(a,b,c){v=!1;var d=a,f=w.outerHeight()-B.outerHeight();if(b&&(d=parseInt(B.css('top'))+a*parseInt(e.wheelStep)/100*B.outerHeight(),d=Math.min(Math.max(d,0),f),d=a>0?Math.ceil(d):Math.floor(d),B.css({top:d+'px'})),q=parseInt(B.css('top'))/(w.outerHeight()-B.outerHeight()),d=q*(w[0].scrollHeight-w.outerHeight()),c){d=a;var g=d/w[0].scrollHeight*w.outerHeight();g=Math.min(Math.max(g,0),f),B.css({top:g+'px'})}w.scrollTop(d),w.trigger('slimscrolling',~~d),i(),j()}function g(){a.addEventListener?(this.addEventListener('DOMMouseScroll',d,!1),this.addEventListener('mousewheel',d,!1),this.addEventListener('MozMousePixelScroll',d,!1)):document.attachEvent("onmousewheel",d)}function h(){p=Math.max(w.outerHeight()/w[0].scrollHeight*w.outerHeight(),u),B.css({height:p+'px'});var a=p==w.outerHeight()?'none':'block';B.css({display:a})}function i(){if(h(),clearTimeout(n),q==~~q){if(v=e.allowPageScroll,r!=q){var a=0==~~q?'top':'bottom';w.trigger('slimscroll',a)}}else v=!1;return r=q,p>=w.outerHeight()?void(v=!0):(B.stop(!0,!0).fadeIn('fast'),void(e.railVisible&&A.stop(!0,!0).fadeIn('fast')))}function j(){e.alwaysVisible||(n=setTimeout(function(){e.disableFadeOut&&k||l||m||(B.fadeOut('slow'),A.fadeOut('slow'))},1e3))}var k,l,m,n,o,p,q,r,s='<div></div>',u=30,v=!1,w=b(this);if(w.parent().hasClass(e.wrapperClass)){var x=w.scrollTop();if(B=w.parent().find('.'+e.barClass),A=w.parent().find('.'+e.railClass),h(),b.isPlainObject(c)){if('height'in c&&'auto'==c.height){w.parent().css('height','auto'),w.css('height','auto');var y=w.parent().parent().height();w.parent().css('height',y),w.css('height',y)}if('scrollTo'in c)x=parseInt(e.scrollTo);else if('scrollBy'in c)x+=parseInt(e.scrollBy);else if('destroy'in c)return B.remove(),A.remove(),void w.unwrap();f(x,!1,!0)}}else{e.height='auto'==e.height?w.parent().height():e.height;var z=b(s).addClass(e.wrapperClass).css({position:'relative',overflow:'hidden',width:e.width,height:e.height});w.css({overflow:'hidden',width:e.width,height:e.height});var A=b(s).addClass(e.railClass).css({width:e.size,height:'100%',position:'absolute',top:0,display:e.alwaysVisible&&e.railVisible?'block':'none','border-radius':e.railBorderRadius,background:e.railColor,opacity:e.railOpacity,zIndex:90}),B=b(s).addClass(e.barClass).css({background:e.color,width:e.size,position:'absolute',top:0,opacity:e.opacity,display:e.alwaysVisible?'block':'none','border-radius':e.borderRadius,BorderRadius:e.borderRadius,MozBorderRadius:e.borderRadius,WebkitBorderRadius:e.borderRadius,zIndex:99}),C='right'==e.position?{right:e.distance}:{left:e.distance};A.css(C),B.css(C),w.wrap(z),w.parent().append(B),w.parent().append(A),e.railDraggable&&B.bind("mousedown",function(a){var c=b(document);return m=!0,t=parseFloat(B.css('top')),pageY=a.pageY,c.bind("mousemove.slimscroll",function(a){currTop=t+a.pageY-pageY,B.css('top',currTop),f(0,B.position().top,!1)}),c.bind("mouseup.slimscroll",function(a){m=!1,j(),c.unbind('.slimscroll')}),!1}).bind("selectstart.slimscroll",function(a){return a.stopPropagation(),a.preventDefault(),!1}),A.hover(function(){i()},function(){j()}),B.hover(function(){l=!0},function(){l=!1}),w.hover(function(){k=!0,i(),j()},function(){k=!1,j()}),w.bind('touchstart',function(a,b){a.originalEvent.touches.length&&(o=a.originalEvent.touches[0].pageY)}),w.bind('touchmove',function(a){if(v||a.originalEvent.preventDefault(),a.originalEvent.touches.length){var b=(o-a.originalEvent.touches[0].pageY)/e.touchScrollStep;f(b,!0),o=a.originalEvent.touches[0].pageY}}),h(),'bottom'===e.start?(B.css({top:w.outerHeight()-B.outerHeight()}),f(0,!0)):'top'!==e.start&&(f(b(e.start).position().top,null,!0),e.alwaysVisible||B.hide()),g()}}),this}}),jQuery.fn.extend({slimscroll:jQuery.fn.slimScroll})});