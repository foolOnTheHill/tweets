package com.georgeoliveira.tweets.common.tweets.outbox.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity
@Table(schema = "public", name = "tweets_outbox")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class TweetOutbox {
  @Id
  @Column(name = "event_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Exclude
  UUID eventId;

  @Column(name = "aggregate_id")
  String aggregateId;

  @Column(name = "type")
  String type;

  @Type(type = "jsonb")
  @Column(name = "aggregate", columnDefinition = "jsonb")
  String aggregate;

  @Column(name = "created_at", insertable = false)
  @EqualsAndHashCode.Exclude
  Timestamp createdAt;
}
