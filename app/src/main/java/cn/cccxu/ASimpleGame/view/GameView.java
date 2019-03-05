package cn.cccxu.ASimpleGame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import cn.cccxu.ASimpleGame.utils.ImageUtil;
import cn.cccxu.ASimpleGame.board.GameService;
import cn.cccxu.ASimpleGame.utils.LinkInfo;


public class GameView extends View {

    private GameService gameService;

    private Piece selectedPiece;

    private LinkInfo linkInfo;

    private Paint paint;

    private Bitmap selectImage;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
        this.paint.setStrokeWidth(3);
        this.selectImage = ImageUtil.getSelectImage(context);
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        this.linkInfo = linkInfo;
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.gameService == null) {
            return;
        }
        Piece[][] pieces = gameService.getPieces();
        if (pieces != null) {
            // 遍历pieces二维数组
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    // 如果二维数组中该元素不为空（即有方块），将这个方块的图片画出来
                    if (pieces[i][j] != null) {
                        // 得到这个Piece对象
                        Piece piece = pieces[i][j];
                        if (piece.getPieceImage() != null) {
                            // 根据方块左上角X、Y座标绘制方块
                            canvas.drawBitmap(piece.getPieceImage().getImage(), piece.getBeginX(), piece.getBeginY(), null);
                        }
                    }
                }
            }
        }
        if (this.selectedPiece != null) {
            canvas.drawBitmap(this.selectImage, this.selectedPiece.getBeginX(),
                    this.selectedPiece.getBeginY(), null);
        }
    }

    // 开始游戏方法
    public void startGame() {
        this.gameService.start();
        this.postInvalidate();
    }
}
