package cn.cccxu.ASimpleGame.board;

import cn.cccxu.ASimpleGame.view.Piece;
import cn.cccxu.ASimpleGame.utils.LinkInfo;

public interface GameService {

    void start();

    Piece[][] getPieces();

    boolean hasPieces();

    Piece findPiece(float touchX, float touchY);

    LinkInfo link(Piece p1, Piece p2);
}
