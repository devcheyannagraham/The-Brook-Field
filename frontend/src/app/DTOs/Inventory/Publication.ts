export class Publication {
  constructor(
    public publicationId: Number,
    public datePublished: Date,
    public isbn: String,
    public genre: String,
    public quantity: Number,
    public title: String
  ) {}
}
