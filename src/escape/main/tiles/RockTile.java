package escape.main.tiles;

import escape.main.graphics.Assets;

public class RockTile extends Tile
{

	public RockTile(int id) {
		super(Assets.stone, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
