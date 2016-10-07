$(document).ready(function(){
		
		$('#upCount').click(function(){
			
			var count = parseInt($('#count').text());
			
			$('#count').text(count+1);
			
		})
		
		$('#downCount').click(function(){
			
			var count = parseInt($('#count').text());
			
			$('#count').text(count-1);
			
		});
		
	});