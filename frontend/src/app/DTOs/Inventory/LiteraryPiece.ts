import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {LiteraryType} from '../../Enums/LiteraryType';

export class LiteraryPiece extends PublicationItem {
  public literaryType: LiteraryType;

  constructor() {
    super();
    this.publicationItemType = PublicationItemType.LITERARY_PIECE;
  }
}
