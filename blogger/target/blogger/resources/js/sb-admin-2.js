$(document).ready(function () {
	width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
    if (width < 768) {
        $('div.sidebar').addClass('sidebar-show');
        $('.page-wrapper').addClass('fill-page');
    } else {
        $('div.sidebar').removeClass('sidebar-show');
    }
	$('.show-sidebar').on('click', function (e) {
		e.preventDefault();
		$('div.sidebar').toggleClass('sidebar-show');
		$('.page-wrapper').toggleClass('fill-page');
		
	});
});
	
$(function() {
	
    $('#side-menu').metisMenu();

});


//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(document).ready(function() 
{
	var oldIE = false,
	IE9 = false,
	IEMobile = false,
	OperaMini = false,
	iOS = false,
	RTL = ($('html').attr('dir') == 'RTL');
	// Browser check
	(function() 
			{
		var test = document.createElement('div'),
		userAgent = (navigator) ? navigator.userAgent : '';

		if (typeof test.style.borderRadius == 'undefined')
		{
			oldIE = true;
			$('html').addClass('OldIE');
		}
		else if (typeof test.style.msTransform == 'string' && typeof test.style.msTransition == 'undefined')
		{
			IE9 = true;
		}

		IEMobile = (userAgent.indexOf('IEMobile/') > 0);
		OperaMini = (userAgent.indexOf('Opera Mini') > 0);
		iOS = (userAgent.indexOf('like Mac OS X') > 0);

		delete test;
			}) ();
	
	
	//Floating navigation
	function navigateTo(target)
	{
		var item = $(target).filter(':first');
		if (!item.length) return;
	
		var offset = Math.floor(item.offset().top) + 'px';
		$('html, body').animate({scrollTop: offset}, 150);
	}
	
	$('#forumFooter').each(function() {
		var data = $('body').attr('data-floating-nav');
		if (!data || !data.length) return;
	
		var visible = false,
			showTop = (data.indexOf('t') >= 0),
			visibleTop = false,
			showBottom = (data.indexOf('b') >= 0),
			visibleBottom = false,
			inputFocused = false;
	
		var $this = $(this),
			w = $(window),
			body = $('body'),
			content = $('#wrapper .page-wrapper');
			//target = $this.attr('href').split('#')[0];
	
		if (!content.length) return;
		if (!showTop && !showBottom) return;
	
		body.append('<div id="floatingNavigation" />');
		var nav = $('#floatingNavigation');
	
		function check()
		{
			if (inputFocused)
			{
				// Hide
				if (visible)
				{
					nav.css('display', 'none');
					visible = false;
				}
				return;
			}
	
			var windowWidth = w.width(),
				windowHeight = (window.innerHeight) ? window.innerHeight : w.height(),
				contentWidth = content.width(),
				bodyHeight = body.outerHeight(true),
				scroll = w.scrollTop();
	
			if (windowHeight < 350)
			{
				// Hide
				if (visible)
				{
					nav.css('display', 'none');
					visible = false;
				}
				return;
			}
	
			// Check for visible components
			var doShowTop = showTop && (scroll > (windowHeight / 2)),
				doShowBottom = showBottom && (scroll < (bodyHeight - windowHeight * 5 / 4));
	
			if (!doShowTop && !doShowBottom)
			{
				// Hide
				if (visible)
				{
					nav.css('display', 'none');
					visible = false;
				}
				return;
			}
	
			function checkRebuildNavigation()
			{
				if (doShowTop !== visibleTop) return true;
				if (doShowBottom !== visibleBottom) return true;
				return false;
			}
	
			function navigate()
			{
				var target = $(this).attr('data-target');
				navigateTo(target);
				return false;
			}
	
			// Re-create HTML code
			if (checkRebuildNavigation())
			{
				nav.html('');
	
				if (doShowTop)
				{
					nav.append('<a class="floating-top" href="javascript:void(0);" data-target="#header">&uarr;</a>');
				}
				visibleTop = doShowTop;
	
				if (doShowBottom)
				{
					nav.append('<a class="floating-bottom" href="javascript:void(0);" data-target="#forumFooter">&darr;</a>');
				}
				visibleBottom = doShowBottom;
	
				nav.find('a').click(navigate);
			}
	
			var diff = 0;
			if (iOS && window.innerHeight && (window.innerHeight - w.height()) > 40)
			{
				diff = 44;
			}
	
			// Show it
			visible = true;
			//nav.css(RTL ? 'left' : 'right', ((windowWidth - contentWidth > 30) ? Math.floor((windowWidth - contentWidth) / 2) : (windowWidth > 800 ? 15 : 5)) + 'px').css({
			nav.css(RTL ? 'left' : 'right',  (windowWidth > 800 ? 15 : 5) + 'px').css({
				top: (windowHeight - 32 - diff) + 'px',
				display: 'block'
			});
		}
		check();
		w.on('scroll resize', check);
	
		
	});
});

$(function() {
    $(window).bind("load resize", function() {
        topOffset = 75;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse')
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse')
        }

        height = (this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $(".page-wrapper").css("min-height", (height) + "px");
        }
    })
});