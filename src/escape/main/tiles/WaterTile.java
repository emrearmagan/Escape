package escape.main.tiles;

import escape.main.graphics.Assets;

public class WaterTile extends Tile
{

	public WaterTile(int id) {
		super(Assets.water, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
