package cn.cccxu.ASimpleGame.board;

import java.util.List;

import cn.cccxu.ASimpleGame.utils.GameConf;
import cn.cccxu.ASimpleGame.utils.ImageUtil;
import cn.cccxu.ASimpleGame.view.Piece;
import cn.cccxu.ASimpleGame.view.PieceImage;

public abstract class AbstractBoard {

    protected abstract List<Piece> createPieces(GameConf config, Piece[][] pieces);

    public Piece[][] create(GameConf config) {
        Piece[][] pieces = new Piece[config.getXSize()][config.getYSize()];
        List<Piece> notNullPieces = createPieces(config, pieces);
        List<PieceImage> playImages = ImageUtil.getPlayImages(config.getContext(), notNullPieces.size());
        int imageWidth = GameConf.PIECE_WIDTH;
        int imageHeight = GameConf.PIECE_HEIGHT;

        for (int i = 0; i < notNullPieces.size(); i++) {
            Piece piece = notNullPieces.get(i);
            piece.setPieceImage(playImages.get(i));
            piece.setBeginX(piece.getIndexX() * imageWidth + config.getBeginImageX());
            piece.setBeginY(piece.getIndexY() * imageHeight + config.getBeginImageY());
            pieces[piece.getIndexX()][piece.getIndexY()] = piece;
        }
        return pieces;
    }
}
