$(function () {
  $('[data-toggle="tooltip"]').tooltip();
  
  $('.semestre').mask("0000.0");
  $('.periodo').mask("0º");
  $('.ch').mask("999");
  $('.nota').mask("99.99");
  $('.horario').mask("99:99-99:99");
  $('.horario-coord').mask("99:99-99:99/99:99-99:99");
  $('.telefone').mask("(99)90000-0000");
  $('.cpf').mask("000.000.000-00");
    
  
  $('.js-currency').maskMoney({decimal:',',thousands:'.', allowZero:true});
  
  $('.js-atualizar-status').on('click', function(event){
	  event.preventDefault();
	  
	  var btnEntregar = $(event.currentTarget);
	  var urlEntregar = btnReceber.attr('href');
	  
	  var response = $.ajax({
		  url: urlEntregar,
		  type: 'PUT'
	  });
	  
	  response.done(function(e){
		  var codigo = btnReceber.data('codigo')
		  $('[data-role=' + codigo + ']').html('<span class="label label-success">'+ e +'</span>')
		  btnEntregar.hide();
		  
	  });
	  
	  response.fail(function(e){
		  alert('Falha ao entregar requisição')
	  });
	  
  });
})

var AW=AW||{};
AW.onSidebarToggleRequest=function(a){
	a.preventDefault(),$(this).blur(),$(".js-sidebar, .js-content").toggleClass("is-toggled")
},
AW.onSearchModalShowRequest=function(a){a.preventDefault(),
	$(".js-search-modal").fadeIn("slow"),
	$("body").addClass("aw-no-scroll"),
	$(".js-search-modal-input").val("").select()},
	
AW.onSearchModalCloseRequest=function(a){a.preventDefault(),
		$(".js-search-modal").hide(),
		$("body").removeClass("aw-no-scroll")},

AW.showLoadingComponent=function(){
			$(".js-loading-overlay").css("display","table").hide().fadeIn("slow")},AW.hideLoadingComponent=function(){$(".js-loading-component").fadeOut("fast")},
AW.initMenu=function(){$(".js-menu > ul > li > a").bind("click",AW.onMenuGroupClick),$(".aw-menu__item .is-active").parents(".aw-menu__item").addClass("is-expanded is-active")},$(function(){AW.init&&AW.init(),AW.initMenu(),$(".js-tooltip").tooltip(),$(".js-sidebar-toggle").bind("click",AW.onSidebarToggleRequest),$(".js-search-modal-trigger-show").bind("click",AW.onSearchModalShowRequest),$(".js-search-modal-close").bind("click",AW.onSearchModalCloseRequest)});
