/*
 * Druid - a distributed column store.
 * Copyright (C) 2012  Metamarkets Group Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.metamx.druid.query.search;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Predicate;



import java.util.List;

/**
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "insensitive_contains", value = InsensitiveContainsSearchQuerySpec.class),
    @JsonSubTypes.Type(name = "fragment", value = FragmentSearchQuerySpec.class)
})
public interface SearchQuerySpec
{
  /**
   *  Deprecated!
   *
   *  This has been moved to the SearchQuery and is only still here for backwards compatibility purposes.  Search
   *  queries should be adjusted to use the sort parameter on the SearchQuery object itself rather than on this
   *  object.  This method will eventually go away.
   *
   * @return
   */
  @Deprecated
  public SearchSortSpec getSearchSortSpec();

  public boolean accept(String dimVal);

  public byte[] getCacheKey();
}
