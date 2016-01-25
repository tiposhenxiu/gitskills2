$(function(){
  $('.clickToolTip').click(function(){
    // リンクの #note** を取得
    var targetNote = $(this).attr('target');
 
    // [?]の座標を取得
    var position = $(this).position();
    var newPositionTop = position.top -11;        /* + 数値で下方向へ移動 */
    var newPositionLeft = position.left + 25;      /* + 数値で右方向へ移動 */
 
    // ツールチップの位置を調整
    $('p'+targetNote).css({'top': newPositionTop + 'px', 'left': newPositionLeft + 'px'});
 
    // ツールチップの class="invisible" を削除
    $('p'+targetNote).removeClass('invisible');
  });
 
  // 表示されたツールチップを隠す処理（マウスクリックで全て隠す）
  $('html').mousedown(function(event){
      // イベント発生源である要素を取得（クリックされた要素を取得）
      var clickedElement = event.target;

      // 取得要素が"toolTip"クラスを持ってなかったら、ツールチップを非表示に
      if(!$(clickedElement).hasClass('tips')){
          $('p.tips').addClass('invisible');
      }
  });
});