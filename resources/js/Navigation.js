  	function changeActiveNav(elementId)
  	{
  		// this element already has the active class so DO NOTHING!
  		if (!$(elementId).hasClass('active')){
  			
  	  		// remove the active class from the current holder
  	  		$('.top_nav li .active').each(function(){
  	  			$(this).removeClass('active');
  	  		});
  	  		
  	  		if(!$(elementId).hasClass('active')){
  	  			$(elementId).addClass('active');
  	  		}	
  		}
  	}