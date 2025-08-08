import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {LiteraryType} from '../../Enums/LiteraryType';

export class LiteraryPiece extends PublicationItem {
  public literaryType: LiteraryType;

  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
      this.literaryType = data["literaryType"] || null;
    }
    this.publicationItemType = PublicationItemType.LITERARY_PIECE;
  }
}
